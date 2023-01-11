package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class PushEffect extends AbstractGameEffect {
    private static TextureAtlas.AtlasRegion img;
    private boolean impactHook = false;
    private float x;
    private float x2;
    private float y;
    private float startX;
    private float startX2;
    private float targetX;
    private float targetX2;

    boolean side;

    public PushEffect(float srcX, float x, float y) {
        if (img == null) {
            img = ImageMaster.vfxAtlas.findRegion("combat/weightyImpact");// 25
        }

        side = srcX < x; //if target is on right side of source, true. True = render left side. False = render right side.

        this.startX = x - 300.0F * Settings.scale - (float)img.packedWidth / 2.0F;
        this.startX2 = x + 300.0F * Settings.scale - (float)img.packedWidth / 2.0F;
        this.targetX = x + 120.0F * Settings.scale - (float)img.packedWidth / 2.0F;
        this.targetX2 = x - 120.0F * Settings.scale - (float)img.packedWidth / 2.0F;
        this.x = this.startX;
        this.x2 = this.startX2;
        this.y = y - (float)img.packedHeight / 2.0F;
        this.scale = 0.75F;
        this.startingDuration = this.duration = 1.0F;
        this.rotation = 90.0F;
        this.color = Color.PURPLE.cpy();
        this.color.a = 0.0F;
    }// 42

    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.playA("ATTACK_MAGIC_FAST_3", -0.2F);
        }

        this.duration -= Gdx.graphics.getDeltaTime() * (Settings.FAST_MODE ? 2 : 1);
        if (this.duration < 0.0F) {
            this.isDone = true;
        } else if (this.duration < 0.35F) {
            if (!this.impactHook) {
                this.impactHook = true;
                CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, true);
            }

            this.color.a = Interpolation.fade.apply(0.0F, 0.8F, this.duration * 1.666F);
        } else if (this.duration < 0.6f) {
            this.color.a = Interpolation.fade.apply(0.0F, 0.8F, this.duration * 1.666F);
        }
        else {
            this.color.a = Math.min(1.0f, Interpolation.fade.apply(0.0F, 0.8F, (this.startingDuration - this.duration) / 0.25f));
        }

        this.scale += Gdx.graphics.getDeltaTime() * 6;
        this.x = Interpolation.fade.apply(this.targetX, this.startX, this.duration / this.startingDuration);
        this.x2 = Interpolation.fade.apply(this.targetX2, this.startX2, this.duration / this.startingDuration);
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(0.5F, 0.5F, 0.9F, this.color.a));
        if (side)
            sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * Settings.scale * 0.5F, this.scale * Settings.scale, this.rotation);
        else
            sb.draw(img, this.x2, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * Settings.scale * 0.5F, this.scale * Settings.scale, this.rotation - 180.0F);
        sb.setColor(new Color(0.7F, 0.5F, 0.9F, this.color.a));
        if (side)
            sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * Settings.scale * 0.75F, this.scale * Settings.scale, this.rotation);
        else
            sb.draw(img, this.x2, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * Settings.scale * 0.75F, this.scale * Settings.scale, this.rotation - 180.0F);
        sb.setColor(this.color);
        if (side)
            sb.draw(img, this.x, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation);
        else
            sb.draw(img, this.x2, this.y, (float)img.packedWidth / 2.0F, (float)img.packedHeight / 2.0F, (float)img.packedWidth, (float)img.packedHeight, this.scale * Settings.scale, this.scale * Settings.scale, this.rotation - 180.0F);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}