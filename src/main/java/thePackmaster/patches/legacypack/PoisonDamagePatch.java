package thePackmaster.patches.legacypack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.patches.powerInterfaces.HealthBarRenderPowerPatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.PoisonPower;
import javassist.CtBehavior;
import thePackmaster.powers.legacypack.PoisonMasteryPower;

import java.util.Collections;

public class PoisonDamagePatch {
    @SpirePatch(clz = PoisonLoseHpAction.class, method = "update")
    public static class LostHPUpdate {
        @SpireInsertPatch(locator = Locator.class, localvars = {"amount"})
        public static void Insert(PoisonLoseHpAction __instance, @ByRef int[] amount) {
            amount[0] = calcPoisonDamageWithPower(amount[0]);
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "damage");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    @SpirePatch(clz = AbstractCreature.class, method = "renderRedHealthBar")
    public static class HealthBarRender {
        @SpireInsertPatch(locator = BarLocator.class, localvars = {"poisonAmt"})
        public static void Insert(AbstractCreature __instance, SpriteBatch sb, float x, float y, @ByRef int[] poisonAmt) {
            poisonAmt[0] = calcPoisonDamageWithPower(poisonAmt[0]);
        }
    }

    private static class BarLocator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "hasPower");
            final int[] all = LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher);
            return new int[]{all[all.length - 1]};
        }
    }

    @SpirePatch(clz = HealthBarRenderPowerPatch.RenderPowerHealthBar.class, method = "Insert")
    public static class HealthBarRenderPower {
        @SpireInsertPatch(locator = Locator.class, localvars = {"poisonAmt"})
        public static void FixPoisonAmount(AbstractCreature __instance, SpriteBatch sb, float x, float y, float targetHealthBarWidth, float HEALTH_BAR_HEIGHT, float HEALTH_BAR_OFFSET_Y, @ByRef int[] poisonAmt) {
            poisonAmt[0] = calcPoisonDamageWithPower(poisonAmt[0]);
        }

        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher matcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "hasPower");
                return LineFinder.findInOrder(ctMethodToPatch, Collections.singletonList(matcher), matcher);
            }
        }
    }

    @SpirePatch(clz = PoisonPower.class, method = "updateDescription")
    public static class PoisonDescription {
        @SpirePostfixPatch
        public static void PostFix(PoisonPower __instance) {
            if (AbstractDungeon.player == null) {
                return;
            }
            PoisonMasteryPower pmp = (PoisonMasteryPower) AbstractDungeon.player.getPower(PoisonMasteryPower.POWER_ID);
            if (pmp != null) {
                int newAmount = calcPoisonDamage(__instance.amount, pmp.amount);
                if (__instance.owner != null && !__instance.owner.isPlayer) {
                    __instance.description = PoisonPower.DESCRIPTIONS[2] + newAmount + PoisonPower.DESCRIPTIONS[1];
                } else {
                    __instance.description = PoisonPower.DESCRIPTIONS[0] + newAmount + PoisonPower.DESCRIPTIONS[1];
                }
            }
        }
    }

    public static int calcPoisonDamageWithPower(int poisonAmount) {
        if (AbstractDungeon.player == null) {
            return poisonAmount;
        }
        PoisonMasteryPower pmp = (PoisonMasteryPower) AbstractDungeon.player.getPower(PoisonMasteryPower.POWER_ID);
        if (pmp != null) {
            return calcPoisonDamage(poisonAmount, pmp.amount);
        } else {
            return poisonAmount;
        }
    }

    public static int calcPoisonDamage(int poisonAmount, int powerAmount) {
        return poisonAmount * (1 + powerAmount);
    }
}