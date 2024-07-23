package thePackmaster.patches.marisapack;

import basemod.helpers.CardBorderGlowManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.util.extraicons.ExtraIcons;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.marisapack.AmplifyCard;
import thePackmaster.powers.marisapack.AmplifyPowerHook;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

public class AmplifyPatches {
    public static AbstractCard amplified = null;
    public static boolean amplifiedThisTurn = false, amplifiedThisCombat = false;
    public static int amtAmplifiedThisTurn = 0, amtAmplifiedThisCombat = 0;

    @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
    public static class CatchUse {
        public static boolean didCost = false;

        @SpireInsertPatch(locator = Locator3.class)
        public static void beforeUse(AbstractPlayer __instance, AbstractCard c) {
            if (c instanceof AmplifyCard && ((AmplifyCard) c).shouldAmplify(c)) {
                if (amplified != null) {
                    SpireAnniversary5Mod.logger.error("Previous amplified card didn't correctly resolve. Please yell at (Gk) Erasels to fix.");
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
            return amplified == c && ((AmplifyCard) c).skipUseOnAmplify();
        }

        @SpireInsertPatch(locator = Locator.class)
        public static void afterUse(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster) {
            if (amplified == c) {
                ((AmplifyCard) c).useAmplified(__instance, monster);
                c.superFlash(AmplifyCard.AMPLIFY_GLOW_COLOR.cpy());
                amplifiedThisCombat = true;
                amplifiedThisTurn = true;
                amtAmplifiedThisCombat++;
                amtAmplifiedThisTurn++;
            }
        }

        //Patches the energy use to sum up the amplify cost with original cost
        @SpireInstrumentPatch
        public static ExprEditor sumEnergyCost() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(EnergyManager.class.getName()) && m.getMethodName().equals("use")) {
                        m.replace(
                                "$proceed(" + AmplifyPatches.CatchUse.class.getName() + ".getTotalCost(c, $1));"
                        );
                    }
                }
            };
        }

        public static int getTotalCost(AbstractCard c, int costForTurn) {
            if (c == amplified) {
                costForTurn += ((AmplifyCard) c)._costLogic();
                didCost = true;
            }

            return costForTurn;
        }


        @SpireInsertPatch(locator = Locator2.class)
        public static void beforeEndUseCard(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster) {
            if (amplified == c) {
                if (!didCost) {
                    __instance.energy.use(((AmplifyCard) c)._costLogic());
                } else {
                    didCost = false;
                }
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

    @SpirePatch(clz = AbstractCard.class, method = "renderEnergy")
    public static class RenderTotalCostPatch {
        private static final Texture energyIcon = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("512/marisapack/orbIcon.png"));
        /*
        // Render cost alongside energy cost
        @SpireInstrumentPatch
        public static ExprEditor patch() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(FontHelper.class.getName()) && m.getMethodName().equals("renderRotatedText")) {
                        m.replace("if (this instanceof " + AmplifyCard.class.getName() + ") {" +
                                "$3 = " + RenderTotalCostPatch.class.getName() + ".modifyCostText(this, $3);"+
                                //"$4 = 674.0f * " + Settings.class.getName() + ".scale;" +
                                "}" +
                                "$_ = $proceed($$);");
                    }
                }
            };
        }
        
        public static String modifyCostText(AbstractCard inst, String orgCost) {
            int ampCost = costLogic(inst);
            if (ampCost >= 0)
                return orgCost + "+" + ampCost;
            return orgCost;
        }
        */

        @SpirePostfixPatch
        public static void patch(AbstractCard __instance, SpriteBatch sb) {
            if (__instance instanceof AmplifyCard) {
                int ampCost = costLogic(__instance);
                if(ampCost > 0) {
                    ExtraIcons.icon(energyIcon)
                            .text("+"+ampCost)
                            .drawColor(new Color(1, 1, 1, __instance.transparency))
                            .render(__instance);
                } else if (ampCost == 0) {
                    ExtraIcons.icon(energyIcon)
                            .text("+"+ampCost)
                            .textColor(new Color(0.4F, 1.0F, 0.4F, __instance.transparency))
                            .drawColor(new Color(1, 1, 1, __instance.transparency))
                            .render(__instance);
                }
            }
        }

        private static int costLogic(AbstractCard c) {
            int ampCost = ((AmplifyCard) c)._costLogic();
            if (ampCost > 0 && ampCost + Wiz.getLogicalCardCost(c) <= EnergyPanel.totalCount) {
                return ampCost;
            } else if (ampCost == 0) { // Doesn't support reducing amplify cost as opposed to making it free, however that effect is not planned or implemented
                return 0;
            }
            return -1;
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

    public static void receiveBattleTransition() {
        amplifiedThisCombat = false;
        amplifiedThisTurn = false;
        amtAmplifiedThisCombat = 0;
        amtAmplifiedThisTurn = 0;
    }

    public static void receiveStartOfTurn() {
        amplifiedThisTurn = false;
        amtAmplifiedThisTurn = 0;
    }
}
