package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.compendium.CardLibSortHeader;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButton;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;
import thePackmaster.SpireAnniversary5Mod;

import java.util.Collections;
import java.util.Comparator;

public class CompendiumPatches {
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("Compendium"));
    public static SortHeaderButton packButton = null;

    @SpirePatch2(clz = CardLibSortHeader.class, method = SpirePatch.CONSTRUCTOR)
    public static class AddSortButton {
        //Reduce space between buttons to fit in another one
        @SpirePrefixPatch
        public static void changePositions() {
            ReflectionHacks.setPrivateStaticFinal(CardLibSortHeader.class, "START_X", 400F * Settings.xScale);
            ReflectionHacks.setPrivateStaticFinal(CardLibSortHeader.class, "SPACE_X", 176F * Settings.xScale);
        }

        //Patch into both spaces of the if clause to make sure the button is created
        @SpireInsertPatch(locator = Locator.class, localvars = {"xPosition"})
        public static void addButton(CardLibSortHeader __instance, float xPosition) {
            packButton = new SortHeaderButton(uiStrings.TEXT[0], xPosition + CardLibSortHeader.SPACE_X, 0, __instance);
        }

        //Add custom button to prexisting array
        @SpirePostfixPatch
        public static void addToButtons(CardLibSortHeader __instance) {
            SortHeaderButton[] butts = new SortHeaderButton[__instance.buttons.length + 1];
            System.arraycopy(__instance.buttons, 0, butts, 0, __instance.buttons.length);
            butts[butts.length - 1] = packButton;
            __instance.buttons = butts;
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(CardLibSortHeader.class, "buttons");
                return LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    //Patch didChangeOrder to apply custom sort
    @SpirePatch2(clz = CardLibSortHeader.class, method = "didChangeOrder")
    public static class CustomOrdering {
        @SpirePostfixPatch
        public static void catchSort(CardLibSortHeader __instance, SortHeaderButton button, boolean isAscending) {
            if (button == packButton) {
                packSort(__instance, isAscending);
                __instance.group.sortByStatus(false);
                __instance.justSorted = true;
                button.setActive(true);
            }
        }

        public static String getParnetNameFromObject(Object o) {
            if(o instanceof AbstractCard) {
                String s = SpireAnniversary5Mod.cardParentMap.get(((AbstractCard) o).cardID);
                if(s == null)
                    return "ZZZZZZZ";
                return s;
            }
            return "ZZZZZZZ";
        }
    }

    //Fix sorting being broken when switching between card colors with packs sorting enabled
    @SpirePatch2(clz = CardLibSortHeader.class, method = "setGroup")
    public static class SortCorrectlyOnSwitch {
        @SpirePostfixPatch
        public static void patch(CardLibSortHeader __instance) {
            for(SortHeaderButton b : __instance.buttons) {
                if(b == packButton && (boolean) ReflectionHacks.getPrivate(b, SortHeaderButton.class, "isActive")) {
                    packSort(__instance, true);
                }
            }
        }
    }

    //Reduce Hitbox sizes so they don't overlap due to space changes
    @SpirePatch2(clz = SortHeaderButton.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {String.class, float.class, float.class})
    public static class ReduceButtonHitboxSize {
        @SpireInstrumentPatch
        public static ExprEditor CatchHitboxInit() {
            return new ExprEditor() {
                @Override
                public void edit(NewExpr m) throws CannotCompileException {
                    if (m.getClassName().equals(Hitbox.class.getName())) {
                        m.replace(
                                "{" +
                                        "$_ = $proceed(" + ReduceButtonHitboxSize.class.getName() + ".changeXSize($1), $2);" +
                                        "}");
                    }
                }
            };
        }

        public static float changeXSize(float curSize) {
            return curSize - (40f * Settings.xScale);
        }
    }

    public static void packSort(CardLibSortHeader input, boolean isAscending) {
        if (isAscending) {
            input.group.group.sort(
                    Comparator.comparing(CustomOrdering::getParnetNameFromObject)
                            .thenComparing(o -> ((AbstractCard)o).rarity)
                            .thenComparing(o -> ((AbstractCard) o).name)
            );
        } else {
            input.group.group.sort(Collections.reverseOrder(
                            Comparator.comparing(CustomOrdering::getParnetNameFromObject)
                                    .thenComparing(o -> ((AbstractCard)o).rarity)
                                    .thenComparing(o -> ((AbstractCard) o).name)
                    )
            );
        }
    }
}
