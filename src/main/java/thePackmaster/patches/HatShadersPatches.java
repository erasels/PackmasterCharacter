package thePackmaster.patches;


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

import java.util.Set;


public class HatShadersPatches {

    public static boolean isBufferOn = false;

    private static TextureRegion playerTexture;

    private static ShaderProgram GoldShader;

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
            if (slot.getAttachment().getName().equals("hat")) {
                isBufferOn = true;
                batch.flush();
                if (buffer == null) {
                    buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
                }
                buffer.begin();
                Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                Gdx.gl.glColorMask(true, true, true, true);
                batch.setShader(GoldShader);
                updateShader();
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
                if (playerTexture == null) {
                    playerTexture = new TextureRegion(buffer.getColorBufferTexture());
                    playerTexture.flip(false, true);
                } else {
                    playerTexture.setTexture(buffer.getColorBufferTexture());
                }
                __instance.setShader(GoldShader);
                updateShader();
                __instance.draw(playerTexture,-Settings.VERT_LETTERBOX_AMT, -Settings.HORIZ_LETTERBOX_AMT);
                __instance.flush();
                __instance.setShader(null);
            }
        }
    }




    private static float shaderTimer = 0f;
    private static final float SHADER_STRENGTH = 0.5f;
    private static final float SHADER_SPEED = 0.25f;
    private static final float SHADER_ANGLE = 0f;
    private static final float SHADER_WIDTH = 4f;
    private static final float SHADER_WIDTH_SHOULDER = 0.7f;
    private static final float SHADER_SPEED_SHOULDER = 0.02f;

    private static void initShader() {
        if (GoldShader == null) {
            try {
                GoldShader = new ShaderProgram(Gdx.files.internal("anniv5Resources/shaders/hatshaders/vertex.vs"),
                        Gdx.files.internal("anniv5Resources/shaders/hatshaders/fragment.fs"));
                if (!GoldShader.isCompiled()) {
                    System.err.println(GoldShader.getLog());
                }
                if (GoldShader.getLog().length() > 0) {
                    System.out.println(GoldShader.getLog());
                }
                BaseMod.logger.info("============ Just initialized rainbow Shader");
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
        if (GoldShader != null) {
            if (isShoulder) {
                GoldShader.setUniformf("u_time", shaderTimer);
                GoldShader.setUniformf("u_strength", SHADER_STRENGTH);
                GoldShader.setUniformf("u_speed", SHADER_SPEED_SHOULDER);
                GoldShader.setUniformf("u_angle", SHADER_ANGLE + shaderTimer*3f);
                GoldShader.setUniformf("u_width", SHADER_WIDTH_SHOULDER);
                shaderTimer += Gdx.graphics.getDeltaTime();
            } else {
                GoldShader.setUniformf("u_width", SHADER_WIDTH);
                GoldShader.setUniformf("u_strength", SHADER_STRENGTH);
                GoldShader.setUniformf("u_speed", SHADER_SPEED);
                GoldShader.setUniformf("u_angle", SHADER_ANGLE + shaderTimer*4f);
                shaderTimer += Gdx.graphics.getDeltaTime();
            }
        }

    }

}
