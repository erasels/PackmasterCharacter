package thePackmaster.patches.marisapack;

import basemod.BaseMod;
import basemod.helpers.CardBorderGlowManager;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.powers.marisapack.AmplifyPowerHook;
import thePackmaster.util.Wiz;

public class AmplifyPatches {
    public static AbstractCard amplified = null;

    @SpirePatch2(clz= AbstractPlayer.class, method="useCard")
    public static class CatchUse {
        @SpireInsertPatch(locator = Locator3.class)
        public static void beforeUse(AbstractPlayer __instance, AbstractCard c) {
            if(c instanceof AmplifyCard && ((AmplifyCard) c).shouldAmplify(c)) {
                if(amplified != null) {
                    BaseMod.logger.error("Previous amplified card didn't correctly resolve. Please yell at (Gk) Erasels to fix.");
                }
                amplified = c;
            }
        }

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

        public static boolean amplifySkip(AbstractCard c) {
            return amplified == c && ((AmplifyCard)c).skipUseOnAmplify();
        }

        @SpireInsertPatch(locator = Locator.class)
        public static void afterUse(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster) {
            if (amplified == c) {
                ((AmplifyCard)c).useAmplified(__instance, monster);
                c.superFlash(AmplifyCard.AMPLIFY_GLOW_COLOR.cpy());
            }
        }

        @SpireInsertPatch(locator = Locator2.class)
        public static void beforeEndUseCard(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster) {
            if(amplified == c) {
                __instance.energy.use(((AmplifyCard)c).getAmplifyCost());
                Wiz.p().powers.stream().filter(p -> p instanceof AmplifyPowerHook).forEach(p -> ((AmplifyPowerHook) p).onAmplify(c));
                amplified = null;
            }
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

        //Directly before card.use call
        private static class Locator3 extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "use");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    public static void receivePostInit() {
        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            @Override
            public boolean test(AbstractCard c) {
                return c instanceof AmplifyCard && ((AmplifyCard) c).shouldAmplify(c);
            }

            @Override
            public Color getColor(AbstractCard c) {
                return AmplifyCard.AMPLIFY_GLOW_COLOR.cpy();
            }

            @Override
            public String glowID() {
                return SpireAnniversary5Mod.makeID("AmplifyGlow");
            }
        });
    }
}
