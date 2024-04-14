package thePackmaster.effects.showmanpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.scene.ShinySparkleEffect;

public class MagicianShinySparkle extends ShinySparkleEffect {
    private float x;
    private float y;
    private float sX;
    private float sY;
    private float vX;
    private float vY;
    private float aV;
    private static final int W = 32;

    public MagicianShinySparkle(float x, float y, float vX, float vY) {
       this.x = x;
       this.y = y;
       this.sX = x;
       this.sY = y;
       this.vX = vX;
       this.vY = vY;
    }

    public void update() {
        this.rotation += this.aV * Gdx.graphics.getDeltaTime();
        this.duration -= Gdx.graphics.getDeltaTime();
        this.x = Interpolation.pow2In.apply(this.sX, this.vX, (this.startingDuration - duration) / startingDuration);
        this.y = Interpolation.pow2In.apply(this.sY, this.vY, (this.startingDuration - duration) / startingDuration);
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        if (this.duration > this.startingDuration / 2.0F) {
            float tmp = this.duration - this.startingDuration / 2.0F;
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.startingDuration / 2.0F - tmp) / 2.0F;
        } else {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, this.duration / (this.startingDuration / 2.0F)) / 2.0F;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(ImageMaster.WOBBLY_ORB_VFX, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * MathUtils.random(1.0F, 1.2F), this.scale / 4.0F, 0.0F, 0, 0, 32, 32, false, false);
        sb.draw(ImageMaster.WOBBLY_ORB_VFX, this.x - 16.0F, this.y - 16.0F, 16.0F, 16.0F, 32.0F, 32.0F, this.scale * MathUtils.random(1.0F, 1.5F), this.scale / 4.0F, 90.0F, 0, 0, 32, 32, false, false);
    }

    public void dispose() {
    }
}