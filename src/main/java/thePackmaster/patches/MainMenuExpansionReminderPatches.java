package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.scenes.TitleBackground;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import com.megacrit.cardcrawl.ui.buttons.Button;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ui.MainMenuExpansionPacksButton;
import thePackmaster.vfx.GlowParticle;

import java.util.ArrayList;

public class MainMenuExpansionReminderPatches {
    public static Button reminderButton = new MainMenuExpansionPacksButton(
            Settings.WIDTH * 0.95f - (MainMenuExpansionPacksButton.tex.getWidth() /2f),
            Settings.HEIGHT * 0.35f - (MainMenuExpansionPacksButton.tex.getHeight() /2f)
    );

    @SpirePatch2(clz = TitleBackground.class, method = "render")
    public static class RenderPatch {
        @SpirePostfixPatch
        public static void patch(TitleBackground __instance, SpriteBatch sb) {
            if(!SpireAnniversary5Mod.isExpansionLoaded && SpireAnniversary5Mod.initializedStrings) {
                reminderButton.render(sb);

                if(reminderButton.pressed) {
                    effects.forEach(e -> e.render(sb));
                }
            }
        }
    }

    private static ArrayList<AbstractGameEffect> effects = new ArrayList<>();
    private static final float GLOW_TIME = 1f;
    private static float glowtimer = 0;
    @SpirePatch2(clz = TitleBackground.class, method = "update")
    public static class UpdatePatch {
        @SpirePostfixPatch
        public static void patch(TitleBackground __instance) {
            if(!SpireAnniversary5Mod.isExpansionLoaded && SpireAnniversary5Mod.initializedStrings) {
                reminderButton.update();

                if(reminderButton.pressed) {
                    effects.forEach(AbstractGameEffect::update);
                    effects.removeIf(e -> e.isDone);
                }
            }
        }
    }

    @SpirePatch2(clz = MenuButton.class, method = "render")
    public static class AddGlowEffect {
        @SpirePostfixPatch
        public static void patch(MenuButton __instance, SpriteBatch sb) {
            if(reminderButton.pressed && __instance.result == MenuButton.ClickResult.QUIT) {
                glowtimer -= Gdx.graphics.getDeltaTime();
                if(glowtimer <= 0) {
                    glowtimer = GLOW_TIME;
                    effects.add(new GlowParticle(highlightImg, __instance.hb.cX, __instance.hb.cY, 0, 1.35f));
                }
            }
        }
    }

    private static Texture highlightImg;
    @SpirePatch2(clz = MenuButton.class, method = SpirePatch.CONSTRUCTOR)
    public static class GetTexture {
        @SpirePostfixPatch
        public static void patch() {
            if(highlightImg == null) {
                highlightImg = ReflectionHacks.getPrivateStatic(MenuButton.class, "highlightImg");
            }
        }
    }
}
