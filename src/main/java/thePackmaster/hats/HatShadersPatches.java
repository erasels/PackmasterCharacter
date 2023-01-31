package thePackmaster.hats;


import basemod.BaseMod;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.esotericsoftware.spine.Skeleton;
import com.esotericsoftware.spine.SkeletonMeshRenderer;
import com.esotericsoftware.spine.Slot;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import javassist.CtBehavior;
import thePackmaster.SpireAnniversary5Mod;


public class HatShadersPatches {


    public static boolean isBufferOn = false;

    private static TextureRegion playerTexture;

    private static ShaderProgram rainbowShader;

    private static FrameBuffer buffer;


    @SpirePatch2(clz = SkeletonMeshRenderer.class, method = "draw", paramtypez = {PolygonSpriteBatch.class, Skeleton.class})
    public static class BeginBuffer {

        private static class BeginBufferLocator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(PolygonSpriteBatch.class, "draw");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }


        @SpireInsertPatch(locator = BeginBufferLocator.class, localvars = {"slot"})
        public static void beginBuffer(PolygonSpriteBatch batch, Slot slot) {
            if (SpireAnniversary5Mod.isHatRainbow && (slot.getAttachment().getName().equals("hat") || slot.getAttachment().getName().equals("packmaster_hat"))) {
                isBufferOn = true;
                batch.flush();
                if (buffer == null) {
                    buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
                }
                buffer.begin();
                Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                Gdx.gl.glColorMask(true, true, true, true);
                batch.setShader(rainbowShader);
                updateShader();
                batch.setBlendFunction(770, 771);
            }
        }
    }

    @SpirePatch2(clz = PolygonSpriteBatch.class, method = "draw", paramtypez = {Texture.class, float[].class, int.class, int.class, short[].class, int.class, int.class})
    public static class EndBuffer {

        @SpirePostfixPatch
        public static void endBuffer(PolygonSpriteBatch __instance) {
            if (isBufferOn) {
                __instance.flush();
                buffer.end();
                isBufferOn = false;
                __instance.setShader(null);
                if (playerTexture == null) {
                    playerTexture = new TextureRegion(buffer.getColorBufferTexture());
                    playerTexture.flip(false, true);
                } else {
                    playerTexture.setTexture(buffer.getColorBufferTexture());
                }
                __instance.draw(playerTexture,-Settings.VERT_LETTERBOX_AMT, -Settings.HORIZ_LETTERBOX_AMT);
                __instance.flush();

            }
        }
    }




    private static float shaderTimer = 0f;
    private static final float SHADER_STRENGTH = 0.6f;
    private static final float SHADER_SPEED = 0.2f;
    private static final float SHADER_ANGLE = 0f;
    private static final float SHADER_WIDTH = 10f;

    private static void initShader() {
        if (rainbowShader == null) {
            try {
                rainbowShader = new ShaderProgram(Gdx.files.internal("anniv5Resources/shaders/hatshaders/rainbowVertex.vs"),
                        Gdx.files.internal("anniv5Resources/shaders/hatshaders/rainbowFragment.fs"));
                if (!rainbowShader.isCompiled()) {
                    System.err.println(rainbowShader.getLog());
                }
                if (rainbowShader.getLog().length() > 0) {
                    System.out.println(rainbowShader.getLog());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void updateShader() {
        updateShader(false);
    }

    public static void updateShader(boolean isShoulder) {
        initShader();
        if (rainbowShader != null) {
            rainbowShader.setUniformf("u_width", SHADER_WIDTH);
            rainbowShader.setUniformf("u_strength", SHADER_STRENGTH);
            rainbowShader.setUniformf("u_speed", SHADER_SPEED);
            rainbowShader.setUniformf("u_angle", SHADER_ANGLE + shaderTimer);
            rainbowShader.setUniformf("u_time", shaderTimer);
            shaderTimer += Gdx.graphics.getDeltaTime();
        }
    }

}
