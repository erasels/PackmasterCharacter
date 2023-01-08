package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.scene.LevelTransitionTextOverlayEffect;
import thePackmaster.screens.PackSetupScreen;

@SpirePatch2(clz = LevelTransitionTextOverlayEffect.class, method = "render")
public class HideExordiumTitlePatch {

    @SpirePrefixPatch
    public static SpireReturn hideWithPackmaster() {
        if (AbstractDungeon.screen == PackSetupScreen.Enum.PACK_SETUP_SCREEN) {
            return SpireReturn.Return();
        }
        return SpireReturn.Continue();
    }
}
