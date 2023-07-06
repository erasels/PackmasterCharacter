package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.scenes.TitleBackground;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;
import com.megacrit.cardcrawl.ui.buttons.Button;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ui.MainMenuExpansionPacksButton;
import thePackmaster.ui.MainMenuFTUE;
import thePackmaster.util.TexLoader;
import thePackmaster.vfx.GlowParticle;

import java.util.ArrayList;

public class MainMenuExpansionReminderPatches {
    //Button logic
    public static Button reminderButton = new MainMenuExpansionPacksButton(
            Settings.WIDTH * 0.93f - ((MainMenuExpansionPacksButton.tex.getWidth() * Settings.xScale) /2f),
            Settings.HEIGHT * 0.35f - ((MainMenuExpansionPacksButton.tex.getHeight() * Settings.yScale) /2f)
    );

    @SpirePatch2(clz = TitleBackground.class, method = "render")
    public static class RenderPatch {
        @SpirePostfixPatch
        public static void patch(TitleBackground __instance, SpriteBatch sb) {
            if(!SpireAnniversary5Mod.isExpansionLoaded && SpireAnniversary5Mod.initializedStrings) {
                reminderButton.render(sb);

                effects.forEach(e -> e.render(sb));
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

                effects.forEach(AbstractGameEffect::update);
                effects.removeIf(e -> e.isDone);
            }
        }
    }

    @SpirePatch2(clz = MenuButton.class, method = "render")
    public static class AddGlowEffectToQuit {
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

    //FTUE Logic
    private static MainMenuFTUE ftue;

    @SpirePatch2(clz = TitleBackground.class, method = "update")
    public static class MenuMenuOpen {
        @SpirePostfixPatch
        public static void patch(TitleBackground __instance) {
            if(!SpireAnniversary5Mod.isExpansionLoaded && __instance.activated && ((float) ReflectionHacks.getPrivate(__instance, TitleBackground.class, "timer")) <= 0 && !SpireAnniversary5Mod.isEPSEEN()) {
                SpireAnniversary5Mod.saveEPSEEN();
                ftue = new MainMenuFTUE(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F, TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/MMFTUEImg.png")));
            }
        }
    }

    @SpirePatch2(clz = MainMenuScreen.class, method = "update")
    public static class FTUEUpdate {
        @SpirePrefixPatch
        public static SpireReturn<?> patch(MainMenuScreen __instance) {
            if(ftue != null) {
                //Darken logic
                __instance.screenColor.a = MathHelper.popLerpSnap(__instance.screenColor.a, 0.8F);
                if(ftue.isDone) {
                    __instance.lighten();
                    ftue = null;
                    return SpireReturn.Continue();
                }

                ftue.update();
                reminderButton.update();
                //If reminder button is clicked while on the fture, close it
                if(!MainMenuExpansionPacksButton.singleTimeSetup) {
                    ftue.isDone = true;
                }
                return SpireReturn.Return();
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch2(clz = MainMenuScreen.class, method = "render")
    public static class FTUERender {
        @SpirePostfixPatch
        public static void patch(SpriteBatch sb) {
            if(ftue != null) {
                ftue.render(sb);
            }
        }
    }
}
