package thePackmaster.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.scenes.TitleBackground;
import com.megacrit.cardcrawl.ui.buttons.Button;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ui.MainMenuExpansionPacksButton;

public class MainMenuExpansionReminderPatches {
    public static Button reminderButton = new MainMenuExpansionPacksButton(
            Settings.WIDTH * 0.95f - (MainMenuExpansionPacksButton.tex.getWidth() /2f),
            Settings.HEIGHT * 0.35f - (MainMenuExpansionPacksButton.tex.getHeight() /2f)
    );
    @SpirePatch2(clz = TitleBackground.class, method = "render")
    public static class RenderPatch {
        @SpirePostfixPatch
        public static void patch(TitleBackground __instance, SpriteBatch sb) {
            if(!SpireAnniversary5Mod.isExpansionLoaded) {
                reminderButton.render(sb);
            }
        }
    }

    @SpirePatch2(clz = TitleBackground.class, method = "update")
    public static class UpdatePatch {
        @SpirePostfixPatch
        public static void patch(TitleBackground __instance) {
            if(!SpireAnniversary5Mod.isExpansionLoaded) {
                reminderButton.update();
            }
        }
    }
}
