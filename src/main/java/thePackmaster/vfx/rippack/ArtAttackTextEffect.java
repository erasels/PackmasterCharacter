package thePackmaster.vfx.rippack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactCurvyEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.makeShaderPath;

public class ArtAttackTextEffect extends AbstractGameEffect {
    private static final float EFFECT_DUR = 1.0F;

    private float x;

    private float y;

    private float targetY;

    private static TextureAtlas.AtlasRegion img;

    private boolean impactHook = false;

    private static ShaderProgram shader = null;

    private static void initShader() {
        if (shader == null) {
            try {
                shader = new ShaderProgram(
                        Gdx.files.internal(makeShaderPath("rippack/rainbow/vertex.vs")),
                        Gdx.files.internal(makeShaderPath("rippack/rainbow/fragment.fs"))
                );
                if (!shader.isCompiled()) {
                    System.err.println(shader.getLog());
                }
                if (shader.getLog().length() > 0) {
                    System.out.println(shader.getLog());
                }
            } catch (GdxRuntimeException e) {
                System.out.println("ERROR: Failed to init rainbow select shader:");
                e.printStackTrace();
            }
        }
    }

    public ArtAttackTextEffect(float x, float y) {
        this(x, y, new Color(1.0F, 1.0F, 0.1F, 0.0F));
    }

    public ArtAttackTextEffect(float x, float y, Color newColor) {
        if (img == null)
            img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");
        this.scale = Settings.scale;
        this.x = x - img.packedWidth / 2.0F;
        this.y = Settings.HEIGHT - img.packedHeight / 2.0F;
        this.duration = 1.0F;
        this.targetY = y - 180.0F * Settings.scale;
        this.rotation = MathUtils.random(-1.0F, 1.0F);
        this.color = newColor;
    }

    public void update() {
        y = Interpolation.fade.apply(Settings.HEIGHT, this.targetY, 1.0F - this.duration / 1.0F);
        scale += Gdx.graphics.getDeltaTime();
        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            isDone = true;
            CardCrawlGame.sound.play(makeID("RipPack_Splash"));
        } else if (duration < 0.2F) {
            if (!impactHook) {
                impactHook = true;
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.HIGH, ScreenShake.ShakeDur.MED, true);
                int i;
                for (i = 0; i < 5; i++)
                    AbstractDungeon.effectsQueue.add(new DamageImpactCurvyEffect(x + img.packedWidth / 2.0F, y + img.packedWidth / 2.0F));
                for (i = 0; i < 30; i++)
                    AbstractDungeon.effectsQueue.add(new UpgradeShineParticleEffect(x +

                            MathUtils.random(-100.0F, 100.0F) * Settings.scale + img.packedWidth / 2.0F, y +
                            MathUtils.random(-50.0F, 120.0F) * Settings.scale + img.packedHeight / 2.0F));
            }
            color.a = Interpolation.fade.apply(0.0F, 0.5F, 0.2F / duration);
        } else {
            color.a = Interpolation.pow2Out.apply(0.6F, 0.0F, duration / 1.0F);
        }
    }

    public void render(SpriteBatch sb) {
        initShader();
        sb.setShader(shader);
        shader.setUniformf("u_time", duration);
        sb.setBlendFunction(770, 1);
        color.g = 1.0F;
        sb.setColor(color);
        sb.draw(img, x, y + 140.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (duration + 0.2F) * 5.0F, scale *

                MathUtils.random(0.99F, 1.01F) * 0.3F, scale *
                MathUtils.random(0.99F, 1.01F) * 2.0F * (duration + 0.8F), rotation);
        color.g = 0.6F;
        sb.setColor(color);
        sb.draw(img, x, y + 40.0F * Settings.scale, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (duration + 0.2F) * 5.0F, scale *

                MathUtils.random(0.99F, 1.01F) * 0.7F, scale *
                MathUtils.random(0.99F, 1.01F) * 1.3F * (duration + 0.8F), rotation);
        color.g = 0.2F;
        sb.setColor(color);
        sb.draw(img, x, y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight * (duration + 0.2F) * 5.0F, scale *

                MathUtils.random(0.99F, 1.01F), scale *
                MathUtils.random(0.99F, 1.01F) * (duration + 0.8F), rotation);
        sb.setBlendFunction(770, 771);
        sb.setShader(null);
    }

    public void dispose() {}
}
