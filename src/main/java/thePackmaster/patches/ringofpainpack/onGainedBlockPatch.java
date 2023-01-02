package thePackmaster.patches.ringofpainpack;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import thePackmaster.cards.ringofpainpack.IceBeast;

@SpirePatch(
        clz = AbstractCreature.class,
        method = "addBlock",
        paramtypez={
                int.class,
        }

)
// A patch to make Ice Beast work
public class onGainedBlockPatch {
    @SpireInsertPatch(locator = Locator.class, localvars = {"tmp"})
    public static void TriggerOnGainedBlock(AbstractCreature instance, int blockAmount, @ByRef float[] tmp) {
        if (instance.isPlayer) {
            if (tmp[0] > 0.0F) {
                for (AbstractCard card : AbstractDungeon.player.hand.group) {
                    if (card instanceof IceBeast) {
                        ((IceBeast) card).triggerOnBlockGain();
                    }
                }
            }
        }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(MathUtils.class, "floor");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}