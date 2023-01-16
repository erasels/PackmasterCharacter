package thePackmaster.patches.odditiespack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.BufferUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.ImageHelper;

import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;

public class FoilShiny {
    @SpirePatch(clz = AbstractCard.class, method = "render", paramtypez = SpriteBatch.class)
    public static class FoilCardsShine {
        private static final ShaderProgram FOIL_SHINE = new ShaderProgram(SpriteBatch.createDefaultShader().getVertexShaderSource(), Gdx.files.internal("fishingResources/shaders/foil_shine.frag").readString(String.valueOf(StandardCharsets.UTF_8)));

        private static final FrameBuffer fbo = ImageHelper.createBuffer();

        private static int RUNNING_ON_STEAM_DECK = -1;

        private static final String OS = System.getProperty("os.name").toLowerCase();
        public static boolean IS_WINDOWS = (OS.indexOf("win") >= 0);

        public static boolean isOnSteamDeck() {
            if (RUNNING_ON_STEAM_DECK == -1) {
                try {
                    RUNNING_ON_STEAM_DECK = CardCrawlGame.clientUtils.isSteamRunningOnSteamDeck() ? 1 : 0;
                } catch (IllegalAccessError e) {
                    System.out.println("VEX OVERRIDE DETECTED: GOG PLAYER");
                    RUNNING_ON_STEAM_DECK = 0;
                }
            }
            return RUNNING_ON_STEAM_DECK == 1;
        }

        @SpirePrefixPatch
        public static SpireReturn<Void> Prefix(AbstractCard __instance, SpriteBatch spriteBatch) {
            if (!Settings.hideCards) {
                if (PackmasterFoilPatches.isFoil(__instance) && IS_WINDOWS && !isOnSteamDeck()) {
                    TextureRegion t = cardToTextureRegion(__instance, spriteBatch);
                    spriteBatch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
                    ShaderProgram oldShader = spriteBatch.getShader();
                    spriteBatch.setShader(FOIL_SHINE);
                    FOIL_SHINE.setUniformf("x_time", SpireAnniversary5Mod.time);
                    spriteBatch.draw(t, -Settings.VERT_LETTERBOX_AMT, -Settings.HORIZ_LETTERBOX_AMT);
                    spriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                    spriteBatch.setShader(oldShader);
                    return SpireReturn.Return();
                }
            }
            return SpireReturn.Continue();
        }

        public static TextureRegion cardToTextureRegion(AbstractCard card, SpriteBatch sb) {
            sb.end();
            ImageHelper.beginBuffer(fbo);
            sb.begin();
            IntBuffer buf_rgb = BufferUtils.newIntBuffer(16);
            IntBuffer buf_a = BufferUtils.newIntBuffer(16);
            Gdx.gl.glGetIntegerv(GL30.GL_BLEND_EQUATION_RGB, buf_rgb);
            Gdx.gl.glGetIntegerv(GL30.GL_BLEND_EQUATION_ALPHA, buf_a);

            Gdx.gl.glBlendEquationSeparate(buf_rgb.get(0), GL30.GL_MAX);
            Gdx.gl.glBlendEquationSeparate(GL30.GL_FUNC_ADD, GL30.GL_MAX);
            card.render(sb, false);
            Gdx.gl.glBlendEquationSeparate(GL30.GL_FUNC_ADD, GL30.GL_FUNC_ADD);
            Gdx.gl.glBlendEquationSeparate(buf_rgb.get(0), buf_a.get(0));

            sb.end();
            fbo.end();
            sb.begin();
            return ImageHelper.getBufferTexture(fbo);
        }
    }
}
