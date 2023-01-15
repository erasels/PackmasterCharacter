package thePackmaster.patches.summonpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.LoopPower;
import javassist.CtBehavior;
import thePackmaster.orbs.AbstractPackMasterOrb;

import static thePackmaster.util.Wiz.adp;

public class WolfPatch {
    @SpirePatch2(
            clz = AbstractPlayer.class,
            method = "channelOrb"
    )
    public static class UpdateOrbsWhenChannelAction {
        @SpirePostfixPatch
        public static void Postfix() {
            for (AbstractOrb orb : adp().orbs)
                orb.updateDescription();
        }
    }
}