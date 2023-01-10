package thePackmaster.patches.psychicpack;

import basemod.BaseMod;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.clapper.util.classutil.*;
import thePackmaster.actions.psychicpack.WakeUpAction;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

//Goal: Anywhere the player's hand is referenced, replace it with a switch between returning the hand or the draw pile based on isDreaming.
@SpirePatch(
        clz = AbstractPlayer.class,
        method = SpirePatch.CLASS
)
public class DeepDreamPatch {
    private static final Logger logger = LogManager.getLogger("DeepDreamPatch");

    public static SpireField<Boolean> isDreaming = new SpireField<>(()->false);
    public static SpireField<Integer> originalHandSize = new SpireField<>(()-> BaseMod.MAX_HAND_SIZE);
    public static SpireField<Integer> dreamLimit = new SpireField<>(()-> BaseMod.MAX_HAND_SIZE);

    //BaseMod.MAX_HAND_SIZE is set to an extremely large value, as the draw pile itself doesn't have a limit.
    //However, the dream hand will limit itself to the top x cards of the draw pile.

    public static int getDreamLimit() {
        return dreamLimit.get(AbstractDungeon.player);
    }

    private static final int DREAM_LIMIT = Integer.MAX_VALUE - 10101;

    public static void startDream(int dreamLimit) {
        if (!isDreaming(AbstractDungeon.player))
        {
            logger.info("Beginning to dream. Original max hand size is " + BaseMod.MAX_HAND_SIZE);
            DeepDreamPatch.originalHandSize.set(AbstractDungeon.player, BaseMod.MAX_HAND_SIZE);
            DeepDreamPatch.dreamLimit.set(AbstractDungeon.player, dreamLimit);
            DeepDreamPatch.isDreaming.set(AbstractDungeon.player, true);
            BaseMod.MAX_HAND_SIZE = DREAM_LIMIT; //A bit of leeway incase something else tries to increase max hand size.
            dreamHand.setHand(AbstractDungeon.player.drawPile);
        }
        else {
            logger.info("Played another dream. Adjusting max hand size.");
            DeepDreamPatch.dreamLimit.set(AbstractDungeon.player, DeepDreamPatch.dreamLimit.get(AbstractDungeon.player) + dreamLimit);
        }
    }
    public static void wakeUp()
    {
        if (AbstractDungeon.player != null && isDreaming(AbstractDungeon.player))
        {
            isDreaming.set(AbstractDungeon.player, false);
            int diff = BaseMod.MAX_HAND_SIZE - DREAM_LIMIT;
            BaseMod.MAX_HAND_SIZE = originalHandSize.get(AbstractDungeon.player) + diff;
            logger.info("Waking up. Max hand size reset to " + BaseMod.MAX_HAND_SIZE);

            if (!AbstractDungeon.overlayMenu.combatPanelsShown) //if reset occurs at end of combat
            {
                for (AbstractCard c : AbstractDungeon.player.hand.group)
                {
                    c.target_y = -AbstractCard.IMG_HEIGHT;
                }
            }
        }
    }

    public static boolean isDreaming(AbstractPlayer p)
    {
        return isDreaming.get(p);
    }

    public static void triggerEndOfTurn() {
        if (isDreaming.get(AbstractDungeon.player)) {
            AbstractDungeon.actionManager.addToBottom(new WakeUpAction());
        }
    }

    public static void patch(ClassFinder finder, ClassPool pool) throws NotFoundException {
        System.out.println("- Deep Dream patch:");

        // Get ALL classes.
        ClassFilter filter = new AndClassFilter(
                new NotClassFilter(new InterfaceOnlyClassFilter()),
                new GamePackageFilter() //avoids about 4000 classes
        );

        ArrayList<ClassInfo> foundClasses = new ArrayList<>();
        finder.findClasses(foundClasses, filter);

        System.out.println("\t- Potential targets found.\n\t- Patching:");

        int skipped = 0, patched = 0;
        HandAccessEditor exprEditor = new HandAccessEditor();
        Field modified = null;
        boolean alreadyModified;
        Collection<String> references;
        CtMethod[] methods;
        CtConstructor[] constructors;
        CtClass ctClass = null;

        outer:
        for (ClassInfo classInfo : foundClasses)
        {
            try
            {
                ctClass = pool.get(classInfo.getClassName());
                exprEditor.changed = false;

                references = ctClass.getRefClasses();

                for (String s : references) {
                    if (pool.getOrNull(s) == null)
                    {
                        System.out.println("\t\t- Class " + ctClass.getSimpleName() + " refers to an unloaded class, " + s + ", and will be skipped.");
                        continue outer;
                    }
                }

                alreadyModified = ctClass.isModified();

                methods = ctClass.getDeclaredMethods();

                for (CtMethod m : methods)
                {
                    m.instrument(exprEditor);
                }

                constructors = ctClass.getDeclaredConstructors();

                for (CtConstructor c : constructors)
                {
                    c.instrument(exprEditor);
                }

                if (exprEditor.changed)
                {
                    System.out.println("\t\t- Class patched: " + ctClass.getSimpleName());
                    ++patched;
                }
                else
                {
                    ++skipped;
                    if (!alreadyModified) {
                        try {
                            if (modified == null) {
                                modified = ctClass.getClass().getDeclaredField("wasChanged");
                                modified.setAccessible(true);
                            }
                            modified.set(ctClass, false);
                            //System.out.println("\t\t- Marked class as unchanged: " + ctClass.getSimpleName());
                        }
                        catch (NoSuchFieldException | IllegalAccessException e) {
                            System.out.println("\t\t- Failed to mark class as unchanged: " + ctClass.getSimpleName());
                        }
                    }
                }
            }
            catch (NotFoundException e) {
                System.out.println("\t\t- Class not found: " + classInfo.getClassName());
                System.out.println(classInfo);
            }
            catch (CannotCompileException e) {
                System.out.println("\t\t- Error occurred while patching class: " + ctClass.getSimpleName() + "\n");
                e.printStackTrace();
            }
        }
        System.out.println("- Deep Dream patch complete. " + (patched + skipped) + " classes checked. " + patched + " classes changed. " + skipped + " classes unchanged.");
    }

    public static DreamHand dreamHand; //initialized in post-initialize

    public static class DreamHand extends CardGroup {
        public DreamHand() {
            super(CardGroupType.HAND);
        }

        public void setHand(CardGroup sourceGroup) {
            this.group = new DreamHandList<>(sourceGroup.group);
        }

        @Override
        public void refreshHandLayout() {
            super.refreshHandLayout();

            int end = AbstractDungeon.player.drawPile.size() - getDreamLimit();
            AbstractCard c;
            for (int i = 0; i < end; ++i) {
                c = AbstractDungeon.player.drawPile.group.get(i);

                c.current_x = c.target_x = CardGroup.DRAW_PILE_X;
                c.current_y = c.target_y = CardGroup.DRAW_PILE_Y;
                c.setAngle(0.0F, true);
                c.drawScale = c.targetDrawScale = 0.12F;
            }
        }
    }

    private static class HandAccessEditor extends ExprEditor {
        public boolean changed = false;

        @Override
        public void edit(FieldAccess f) throws CannotCompileException {
            if (f.getClassName().equals(AbstractPlayer.class.getName()) && f.getFieldName().equals("hand"))
            {
                if (f.isReader())
                {
                    f.replace("{" +
                                "if (" + DeepDreamPatch.class.getName() + ".isDreaming($0)) {" +
                                    "$_ = " + DeepDreamPatch.class.getName() + ".dreamHand;" +
                                "} else {" +
                                    "$_ = $proceed($$);" +
                                "}" +
                            "}");
                }
                else if (f.isWriter())
                {
                    f.replace("{" +
                                "if (" + DeepDreamPatch.class.getName() + ".isDreaming($0)) {" +
                                    "$0.drawPile = $1;" +
                                    DeepDreamPatch.class.getName() + ".dreamHand.setHand($0.drawPile);" +
                                "} else {" +
                                    "$proceed($$);" +
                                "}" +
                            "}");
                }
                changed = true;
            }
        }
    }

    private static class GamePackageFilter implements ClassFilter {
        private static final Set<String> rejectedPackages;
        static {
            rejectedPackages = new HashSet<>();
            rejectedPackages.add("com.badlogic");
            rejectedPackages.add("com.esotericsoftware");
            rejectedPackages.add("com.fasterxml");
            rejectedPackages.add("com.gikk");
            rejectedPackages.add("com.google");
            rejectedPackages.add("com.jcraft");
            rejectedPackages.add("com.sun");
            rejectedPackages.add("de.robojumper");
            rejectedPackages.add("io.sentry");
            rejectedPackages.add("javazoom.jl");
            rejectedPackages.add("net.arikia");
            rejectedPackages.add("net.java");
            rejectedPackages.add("org.apache");
            rejectedPackages.add("org.lwjgl");
            rejectedPackages.add("org.slf4j");
        }

        public boolean accept(ClassInfo classInfo, ClassFinder classFinder) {
            String name = classInfo.getClassName();
            int secondPackage = name.indexOf('.');
            if (secondPackage >= 0)
            {
                secondPackage = name.indexOf('.', secondPackage + 1);

                if (secondPackage > 0)
                {
                    name = name.substring(0, secondPackage);

                    return !rejectedPackages.contains(name);
                }
            }

            return true;
        }
    }

    public static class DreamHandList<E> extends ArrayList<E>
            implements List<E>, RandomAccess, Cloneable {
        private final ArrayList<E> parent;

        //the sub array list represents up to limit objects of the parent arraylist starting from index 0
        public DreamHandList(ArrayList<E> parent) {
            this.parent = parent;
        }

        //non-modifying or accessing methods simply refer straight to parent
        @Override
        public void trimToSize() {
            parent.trimToSize();
        }

        @Override
        public void ensureCapacity(int minCapacity) {
            parent.ensureCapacity(minCapacity);
        }

        @Override
        public int size() {
            return Math.min(DeepDreamPatch.getDreamLimit(), parent.size());
        }

        @Override
        public boolean isEmpty() {
            return parent.isEmpty();
        }

        private int off() {
            return parent.size() - size();
        }
        private List<E> sub() {
            return parent.subList(off(), parent.size());
        }

        @Override
        public boolean contains(Object o) {
            return sub().contains(o);
        }

        @Override
        public int indexOf(Object o) {
            int index = super.indexOf(o) - off();
            if (index < 0) return -1;
            return index;
        }

        @Override
        public int lastIndexOf(Object o) {
            if (o == null) {
                for (int i = parent.size()-1; i >= off(); i--)
                    if (parent.get(i)==null)
                        return i;
            } else {
                for (int i = parent.size()-1; i >= off(); i--)
                    if (o.equals(parent.get(i)))
                        return i;
            }
            return -1;
        }

        @Override
        @SuppressWarnings("unchecked")
        public Object clone() {
            ArrayList<E> parentClone = (ArrayList<E>) parent.clone();

            return new DreamHandList<>(parentClone);
        }

        @Override
        public Object[] toArray() {
            return Arrays.copyOfRange(parent.toArray(), off(), parent.size());
        }
        @SuppressWarnings("unchecked")
        public <T> T[] toArray(T[] a) {
            T[] src = (T[]) Arrays.copyOfRange(parent.toArray(), off(), parent.size(), a.getClass());
            if (a.length < src.length)
                // Make a new array of a's runtime type, but my contents:
                return src;

            System.arraycopy(src, 0, a, 0, src.length);

            if (a.length > src.length)
                a[src.length] = null;
            return a;
        }

        @Override
        public E get(int index) {
            rangeCheck(index);
            return parent.get(off() + index);
        }

        @Override
        public E set(int index, E element) {
            rangeCheck(index);
            return parent.set(off() + index, element);
        }

        @Override
        public boolean add(E e) {
            parent.add(e);
            return true;
        }

        @Override
        public void add(int index, E element) {
            rangeCheckForAdd(index);
            parent.add(off() + index, element);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return parent.addAll(c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            rangeCheckForAdd(index);
            return parent.addAll(off() + index, c);
        }

        @Override
        public E remove(int index) {
            return sub().remove(index);
        }

        @Override
        public boolean remove(Object o) {
            return sub().remove(o);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return sub().removeAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return sub().retainAll(c);
        }

        @Override
        public void clear() {
            if (size() > 0) {
                sub().clear();
            }
        }

        @Override
        public Iterator<E> iterator() {
            return listIterator();
        }

        @Override
        public ListIterator<E> listIterator() {
            return sub().listIterator();
        }

        public ListIterator<E> listIterator(final int index) {
            return sub().listIterator(index);
        }

        private void rangeCheck(int index) {
            if (index >= size())
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > this.size())
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        private String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+this.size();
        }

        public List<E> subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size()); //from and to are <= size, off() + size = parent.size
            return parent.subList(off() + fromIndex, off() + toIndex);
        }

        static void subListRangeCheck(int fromIndex, int toIndex, int size) {
            if (fromIndex < 0)
                throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
            if (toIndex > size)
                throw new IndexOutOfBoundsException("toIndex = " + toIndex);
            if (fromIndex > toIndex)
                throw new IllegalArgumentException("fromIndex(" + fromIndex +
                        ") > toIndex(" + toIndex + ")");
        }

        @Override
        public boolean removeIf(Predicate<? super E> filter) {
            return sub().removeIf(filter);
        }

        @Override
        public void replaceAll(UnaryOperator<E> operator) {
            Objects.requireNonNull(operator);
            for (int i=off(); i < parent.size(); ++i) {
                parent.set(i, operator.apply(parent.get(i)));
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public void sort(Comparator<? super E> c) {
            E[] elements = (E[]) toArray();
            Arrays.sort(elements, c);

            for (int i = 0; i < elements.length; ++i) {
                parent.set(off() + i, elements[i]);
            }
        }
    }

}
