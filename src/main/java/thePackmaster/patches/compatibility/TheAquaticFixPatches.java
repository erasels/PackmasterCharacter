package thePackmaster.patches.compatibility;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.Objects;

@SpirePatch2(cls = "aquaticmod.patches.UseCardPatch", method = "Prefix", optional = true)
public class TheAquaticFixPatches {
    @SpirePrefixPatch
    public static SpireReturn<?> patch() {
        //Jank but its fine, just execute the shitty code if playing Aquatic
        if(Objects.equals(AbstractDungeon.player.getLocalizedCharacterName(), "The Aquatic")) {
            return SpireReturn.Continue();
        } else {
            return SpireReturn.Return(SpireReturn.Continue());
        }
    }
}
