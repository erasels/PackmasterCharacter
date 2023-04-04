package thePackmaster.vfx.instadeathpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.vfx.psychicpack.ScaledAdditiveSlashImpactEffect;

public class QuickStakeEffect extends AbstractGameEffect {
    private boolean first;

    private static TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float fX;
    private float fY;
    private float targetScale;
    private float r, g, b;

    private boolean shownSlash = false, hitSfx = false;

    private final float fullScaleTime;

    public QuickStakeEffect(AbstractCreature source, AbstractCreature target) {
        first = true;


        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/stake");
        }

        float baseX = target.hb.cX + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
        float baseY = target.hb.cY + MathUtils.random(-10.0F, 10.0F) * Settings.scale;

        this.rotation = target.hb.cX > source.hb.cX ? 180 : 0;

        float dist = MathUtils.random(240.0F, 300.0F) * Settings.scale;
        this.x = MathUtils.cosDeg(rotation) * dist + baseX;
        this.y = MathUtils.sinDeg(rotation) * dist + baseY;
        this.tX = MathUtils.cosDeg(rotation) * dist * 1.2f + baseX;
        this.tY = MathUtils.sinDeg(rotation) * dist * 1.2f + baseY;
        this.x -= (float)(img.packedWidth / 2);
        this.y -= (float)(img.packedHeight / 2);
        this.tX -= (float)(img.packedWidth / 2);
        this.tY -= (float)(img.packedHeight / 2);
        this.sX = this.x;
        this.sY = this.y;
        this.fX = baseX - (float)(img.packedWidth / 2);
        this.fY = baseY - (float)(img.packedHeight / 2);

        this.rotation -= 90; //image is 90 degrees off so it needs some extra rotation
        this.duration = this.startingDuration = Settings.FAST_MODE ? 0.4F : 0.7F;
        this.fullScaleTime = this.startingDuration * 0.75f;

        this.scale = 0.2F;
        this.targetScale = MathUtils.random(0.5F, 0.8F);

        this.color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.7F, 0.8F), MathUtils.random(0.8F, 0.9F), 0.0F);
        r = this.color.r;
        g = this.color.g;
        b = this.color.b;
    }

    public void update() {
        if (first) {
            first = false;
            CardCrawlGame.sound.playA("APPEAR", 0.2F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();

        if (this.duration > fullScaleTime) {
            float prog = (this.duration - fullScaleTime) / (this.startingDuration - fullScaleTime);
            this.scale = Interpolation.elasticIn.apply(this.targetScale, this.scale, prog);
            this.color.a = Interpolation.fade.apply(0.75F, 0.0F, prog);

            this.x = Interpolation.fade.apply(this.tX, this.sX, prog);
            this.y = Interpolation.fade.apply(this.tY, this.sY, prog);
        } else if (this.duration > 0) {
            this.scale = this.targetScale;
            this.color.a = 0.75F;
            this.x = Interpolation.exp10Out.apply(this.fX, this.tX, this.duration / fullScaleTime);
            this.y = Interpolation.exp10Out.apply(this.fY, this.tY, this.duration / fullScaleTime);
        }
        else if (this.duration > -0.75f) {
            this.color.a = 0.75F + this.duration;
            float fade = Math.max(0, 1 + (2 * duration));
            this.color.r = r * fade;
            this.color.g = g * fade;
            this.color.b = b * fade;
        }
        else {
            this.isDone = true;
        }

        if (this.duration < 0.05F && !shownSlash) {
            AbstractDungeon.effectsQueue.add(new ScaledAdditiveSlashImpactEffect(this.fX + (float)img.packedWidth / 2.0F, this.fY + (float)img.packedHeight / 2.0F, 0.7f, 1.1f, this.color.cpy()));
            shownSlash = true;
        }
        if (this.duration < 0.0F && !hitSfx) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, MathUtils.randomBoolean());
            CardCrawlGame.sound.playA("ATTACK_FAST", MathUtils.random(0.3F, 0.5F));
            hitSfx = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * MathUtils.random(1.0F, 1.2F) * Settings.scale, this.scale * MathUtils.random(1.0F, 1.2F) * Settings.scale, this.rotation);
        sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * MathUtils.random(0.9F, 1.1F) * Settings.scale, this.scale * MathUtils.random(0.9F, 1.1F) * Settings.scale, this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
