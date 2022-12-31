package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import javassist.*;
import org.clapper.util.classutil.*;
import thePackmaster.packs.AbstractCardPack;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

@SpirePatch(
        clz = AbstractCard.class,
        method = SpirePatch.CLASS
)
public class CardParentPackPatch {
    public static SpireField<AbstractCardPack> parentPack = new SpireField<>(() -> null);

    /*@SpirePatch2(
            clz = AbstractCard.class,
            method = "makeCopy"
    )
    public static class MakeStatEquivalentCopy {
        @SpirePostfixPatch
        public static AbstractCard copyParent(AbstractCard __result, AbstractCard __instance) {
            parentPack.set(__result, parentPack.get(__instance));
            return __result;
        }
    }*/

    public static AbstractCard copyParent(AbstractCard source, AbstractCard target) {
        parentPack.set(target, parentPack.get(source));
        return target;
    }

    //Dynamically patch all makeCopy methods of cards to copy over the parent
    @SpirePatch2(clz = CardCrawlGame.class, method = SpirePatch.CONSTRUCTOR)
    public static class MakeCopyKeepParentPatch {
        public static void Raw(CtBehavior ctBehavior) throws NotFoundException {
            ClassFinder finder = new ClassFinder();

            finder.add(new File(Loader.STS_JAR));

            for (ModInfo modInfo : Loader.MODINFOS) {
                if (modInfo.jarURL != null) {
                    try {
                        finder.add(new File(modInfo.jarURL.toURI()));
                    } catch (URISyntaxException e) {
                        // do nothing
                    }
                }
            }

            // Get all classes for AbstractCard
            ClassFilter filter = new AndClassFilter(
                    new NotClassFilter(new InterfaceOnlyClassFilter()),
                    new ClassModifiersClassFilter(Modifier.PUBLIC),
                    new OrClassFilter(
                            new org.clapper.util.classutil.SubclassClassFilter(AbstractCard.class),
                            (classInfo, classFinder) -> classInfo.getClassName().equals(AbstractCard.class.getName())
                    )
            );

            ArrayList<ClassInfo> foundClasses = new ArrayList<>();
            finder.findClasses(foundClasses, filter);

            for (ClassInfo classInfo : foundClasses) {
                CtClass ctClass = ctBehavior.getDeclaringClass().getClassPool().get(classInfo.getClassName());

                try {
                    CtMethod[] methods = ctClass.getDeclaredMethods();
                    for (CtMethod m : methods) {
                        if (m.getName().equals("makeCopy") && !m.isEmpty()) {
                            m.insertAfter("{" +
                                    "$_ = " + CardParentPackPatch.class.getName() + ".copyParent($0, $_);" +
                                    "}");
                        }
                    }
                } catch (CannotCompileException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}