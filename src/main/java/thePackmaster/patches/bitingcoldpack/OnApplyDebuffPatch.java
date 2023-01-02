package thePackmaster.patches.bitingcoldpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import javassist.CtBehavior;
import thePackmaster.actions.bitingcoldpack.ActivateGlaciateAction;
import thePackmaster.cards.bitingcoldpack.GrowingAffliction;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.powers.bitingcoldpack.GlaciatePower;

import java.lang.reflect.Field;

// Patch for effects that activate upon the player applying a debuff
@SpirePatch(clz = ApplyPowerAction.class, method = "update")
public class OnApplyDebuffPatch {
    @SpireInsertPatch(locator = Locator.class)
    public static void Insert(ApplyPowerAction __instance) throws NoSuchFieldException, IllegalAccessException {
        Field f = ApplyPowerAction.class.getDeclaredField("powerToApply");
        f.setAccessible(true);
        AbstractPower thisPower = (AbstractPower) f.get(__instance);
        if (thisPower.type == AbstractPower.PowerType.DEBUFF && __instance.source == AbstractDungeon.player && __instance.target != AbstractDungeon.player) {
            // Growing Affliction (Return to hand)
            for (AbstractCard c : AbstractDungeon.player.discardPile.group)
                if (c.cardID.equals(GrowingAffliction.ID))
                    AbstractDungeon.actionManager.addToBottom(new DiscardToHandAction(c));

            // Glaciate (Gain Vigor)
            if (thisPower.ID.equals(FrostbitePower.POWER_ID) && __instance.source.hasPower(GlaciatePower.POWER_ID))
                AbstractDungeon.actionManager.addToBottom(new ActivateGlaciateAction(__instance.source.getPower(GlaciatePower.POWER_ID)));
        }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCreature.class, "useFastShakeAnimation");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}