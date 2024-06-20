package thePackmaster.patches.summonpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.orbs.AbstractOrb;

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