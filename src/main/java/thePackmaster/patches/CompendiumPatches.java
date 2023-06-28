package thePackmaster.patches;

import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.EverythingFix;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.compendium.CardLibSortHeader;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
import com.megacrit.cardcrawl.screens.mainMenu.SortHeaderButton;
import com.megacrit.cardcrawl.ui.buttons.ReturnToMenuButton;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import javassist.expr.NewExpr;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.patches.shamanpack.FineTuneLineWidthPatch;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CompendiumPatches {
    public static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("Compendium"));
    public static SortHeaderButton packButton = null;
    public static ArrayList<AbstractCard.CardColor> allowedCardColors = new ArrayList<>(Arrays.asList(AbstractCard.CardColor.RED, AbstractCard.CardColor.GREEN, AbstractCard.CardColor.BLUE, AbstractCard.CardColor.PURPLE));

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
            if (button == packButton && RenderBaseGameCardPackTopTextPatches.isInPackmasterCardLibraryScreen()) {
                packSort(__instance, isAscending);
                __instance.group.sortByStatus(false);
                __instance.justSorted = true;
                button.setActive(true);
            }
        }

        public static String getParnetNameFromObject(Object o) {
            if(o instanceof AbstractCard) {
                String packId = SpireAnniversary5Mod.cardParentMap.get(((AbstractCard) o).cardID);
                if(packId == null)
                    return "ZZZZZZZ";
                return SpireAnniversary5Mod.packsByID.get(packId).name;
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
                if(b == packButton && (boolean) ReflectionHacks.getPrivate(b, SortHeaderButton.class, "isActive") && RenderBaseGameCardPackTopTextPatches.isInPackmasterCardLibraryScreen()) {
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

    @SpirePatch2(clz = CardLibSortHeader.class, method = "renderButtons")
    public static class HideSortButtonForOtherTabs {
        public static class HideSortButtonForOtherTabsExprEditor extends ExprEditor {
            @Override
            public void edit(MethodCall methodCall) throws CannotCompileException {
                if (methodCall.getClassName().equals(SortHeaderButton.class.getName()) && methodCall.getMethodName().equals("render")) {
                    methodCall.replace(String.format("{ if(%1$s.isInPackmasterCardLibraryScreen() || $0 != %2$s.packButton) { $proceed($$); } }", RenderBaseGameCardPackTopTextPatches.class.getName(), CompendiumPatches.class.getName()));
                }
            }
        }

        @SpireInstrumentPatch
        public static ExprEditor HideSortButtonForOtherTabsPatch() {
            return new HideSortButtonForOtherTabs.HideSortButtonForOtherTabsExprEditor();
        }
    }

    @SpirePatch2(clz = SortHeaderButton.class, method = "update")
    public static class DisableClickForOtherTabs {
        @SpirePrefixPatch
        public static SpireReturn<Void> disableClickForOtherTabs(SortHeaderButton __instance) {
            if (__instance == packButton && !RenderBaseGameCardPackTopTextPatches.isInPackmasterCardLibraryScreen()) {
                return SpireReturn.Return();
            }
            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(clz = EverythingFix.Initialize.class, method = "Insert")
    public static class ShowBaseGameCards {
        @SpirePostfixPatch
        public static void showBaseGameCards(Object __obj_instance) {
            if (EverythingFix.Fields.cardGroupMap.containsKey(ThePackmaster.Enums.PACKMASTER_RAINBOW)) {
                CardGroup group = EverythingFix.Fields.cardGroupMap.get(ThePackmaster.Enums.PACKMASTER_RAINBOW);
                List<AbstractCard> baseGameCards = CardLibrary.getAllCards().stream()
                        .filter(c -> (allowedCardColors.contains(c.color)))
                        .filter(c -> SpireAnniversary5Mod.cardParentMap.containsKey(c.cardID))
                        .collect(Collectors.toList());
                group.group.addAll(baseGameCards);

                // We've found that having the special rarity cards associated with the packs show up in the Packmaster's
                // tab is not very useful, and we want to have each pack of 10 cards in two nicely organized rows (when
                // sorted by packs). In the future we might make all these cards colorless (and remove or change this code),
                // but for now we just move them to the list that the compendium uses for colorless cards
                List<AbstractCard> specialRarityCards = group.group.stream().filter(c -> c.rarity == AbstractCard.CardRarity.SPECIAL).collect(Collectors.toList());
                CardGroup colorlessCards = ReflectionHacks.getPrivate(__obj_instance, CardLibraryScreen.class, "colorlessCards");
                colorlessCards.group.addAll(specialRarityCards);
                group.group.removeIf(c -> c.rarity == AbstractCard.CardRarity.SPECIAL);
            }
        }
    }
}
