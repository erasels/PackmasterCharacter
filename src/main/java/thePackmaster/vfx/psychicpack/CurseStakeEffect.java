package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class CurseStakeEffect extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion img;
    private float x;
    private float y;
    private float sX;
    private float sY;
    private float tX;
    private float tY;
    private float fX;
    private float fY;
    private float targetAngle;
    private float startingAngle;
    private float targetScale;
    private boolean shownSlash = false;

    public CurseStakeEffect(float x, float y) {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/stake");
        }

        this.targetAngle = MathUtils.random(0.0f, 360.0f);
        float dist = MathUtils.random(100.0F, 150.0F);
        this.x = MathUtils.cosDeg(targetAngle) * dist * Settings.scale + x;
        this.y = MathUtils.sinDeg(targetAngle) * dist * Settings.scale + y;
        this.tX = MathUtils.cosDeg(targetAngle) * dist * Settings.scale * 1.2f + x;
        this.tY = MathUtils.sinDeg(targetAngle) * dist * Settings.scale * 1.2f + y;
        this.x -= (float)(img.packedWidth / 2);
        this.y -= (float)(img.packedHeight / 2);
        this.tX -= (float)(img.packedWidth / 2);
        this.tY -= (float)(img.packedHeight / 2);
        this.sX = this.x;
        this.sY = this.y;
        this.fX = x - (float)(img.packedWidth / 2);
        this.fY = y - (float)(img.packedHeight / 2);

        this.targetAngle -= 90; //image is 90 degrees off so it needs some extra rotation
        this.duration = 1.0F;
        this.scale = 0.01F;
        this.targetScale = MathUtils.random(0.5F, 0.8F);
        this.startingAngle = MathUtils.random(0.0F, 360.0F);
        this.rotation = this.startingAngle;
        this.color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.0F, 0.2F), MathUtils.random(0.1F, 0.4F), 0.0F);
    }

    public void update() {
        this.rotation = Interpolation.elasticIn.apply(this.targetAngle, this.startingAngle, Math.max(0, (this.duration - 0.3f) * 1.5f));
        if (this.duration > 0.5F) {
            this.scale = Interpolation.elasticIn.apply(this.targetScale, this.targetScale * 10.0F, (this.duration - 0.5F) * 2.0F) * Settings.scale;
            this.color.a = Interpolation.fade.apply(0.6F, 0.0F, (this.duration - 0.5F) * 2.0F);

            this.x = Interpolation.fade.apply(this.tX, this.sX, (this.duration - 0.5f) * 2.0f);
            this.y = Interpolation.fade.apply(this.tY, this.sY, (this.duration - 0.5f) * 2.0f);
        } else {
            this.x = Interpolation.exp10Out.apply(this.fX, this.tX, this.duration * 2.0F);
            this.y = Interpolation.exp10Out.apply(this.fY, this.tY, this.duration * 2.0F);
        }

        this.duration -= Gdx.graphics.getDeltaTime();

        if (this.duration < 0.05F && !this.shownSlash) {
            AbstractDungeon.effectsQueue.add(new ScaledAdditiveSlashImpactEffect(this.fX + (float)img.packedWidth / 2.0F, this.fY + (float)img.packedHeight / 2.0F, 0.7f, 1.1f, this.color.cpy()));
            this.shownSlash = true;
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, MathUtils.randomBoolean());
            CardCrawlGame.sound.playA("ATTACK_FAST", MathUtils.random(0.2F, 0.4F));
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(this.color);
        sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * MathUtils.random(1.0F, 1.2F), this.scale * MathUtils.random(1.0F, 1.2F), this.rotation);
        sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * MathUtils.random(0.9F, 1.1F), this.scale * MathUtils.random(0.9F, 1.1F), this.rotation);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}