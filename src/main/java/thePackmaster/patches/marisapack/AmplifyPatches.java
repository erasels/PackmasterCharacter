package thePackmaster.patches.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.util.Wiz;

public class AmplifyPatches {
    @SpirePatch2(clz= AbstractPlayer.class, method="useCard")
    public static class CatchUse {
        private static boolean amplified = false;

        @SpireInstrumentPatch
        public static ExprEditor amplifyUse() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(AbstractCard.class.getName()) && m.getMethodName().equals("use")) {
                        m.replace(
                    "if(!" + CatchUse.class.getName() + ".amplifySkip(c)) {" +
                                    "$proceed($$);" +
                                "}");
                    }
                }
            };
        }

        @SpireInsertPatch(locator = Locator.class)
        public static void afterUse(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster) {
            if (shouldAmplify(c)) {
                ((AmplifyCard)c).useAmplified(__instance, monster);
                c.superFlash(AmplifyCard.AMPLIFY_GLOW_COLOR.cpy());
                amplified = true;
            }
        }

        @SpireInsertPatch(locator = Locator.class)
        public static void beforeEndUseCard(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster) {
            if(amplified) {
                __instance.energy.use(((AmplifyCard)c).getAmplifyCost());
                amplified = false;
            }
        }

        public static boolean amplifySkip(AbstractCard c) {
            return shouldAmplify(c) && ((AmplifyCard)c).skipUseOnAmplify();
        }

        //Directly after card.use call
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.NewExprMatcher(UseCardAction.class);
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }

        //Above the last if clause
        private static class Locator2 extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "canUseAnyCard");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    public static boolean shouldAmplify(AbstractCard c) {
        int cardCost = Wiz.getLogicalCardCost(c);
        System.out.printf("Logical cost: %s\nEnergy on use: %s\n", cardCost, c.energyOnUse);
        return c instanceof AmplifyCard &&
                EnergyPanel.totalCount >= cardCost + ((AmplifyCard)c).getAmplifyCost();
    }
}
