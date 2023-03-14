package thePackmaster.vfx.spherespack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BlazeOrbActivateParticle extends AbstractGameEffect {
    private float x;
    private float y;
    private float scaleY;
    private float aV;
    private static final int W = 140;
    private boolean flipHorizontal;
    private boolean flipVertical;

    public BlazeOrbActivateParticle(float x, float y) {
        this.x = x;
        this.y = y;
        this.renderBehind = true;
        this.duration = 0.25F;
        this.startingDuration = 0.25F;
        this.color = new Color(MathUtils.random(0.8F, 1.0F), MathUtils.random(0.0F, 0.3F), MathUtils.random(0.0F, 0.3F), 0.5F);
        this.scale = MathUtils.random(1.0F, 2.0F) * Settings.scale;
        this.rotation = MathUtils.random(-8.0F, 8.0F);
        this.flipHorizontal = MathUtils.randomBoolean();
        this.flipVertical = MathUtils.randomBoolean();
        this.scale = Settings.scale;
        this.scaleY = 2.0F * Settings.scale;
        this.aV = MathUtils.random(-100.0F, 100.0F);
    }

    public void update() {
        this.rotation += Gdx.graphics.getDeltaTime() * this.aV;
        this.scale = Interpolation.pow4Out.apply(5.0F, 1.0F, this.duration * 4.0F) * Settings.scale;
        this.scaleY = Interpolation.bounceOut.apply(0.2F, 2.0F, this.duration * 4.0F) * Settings.scale;
        this.color.a = Interpolation.pow5Out.apply(0.01F, 0.5F, this.duration * 4.0F);
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.setBlendFunction(770, 1);
        sb.draw(ImageMaster.DARK_ORB_ACTIVATE_VFX, this.x - 70.0F, this.y - 70.0F, 70.0F, 70.0F, 140.0F, 140.0F, this.scale, this.scaleY, this.rotation, 0, 0, 140, 140, this.flipHorizontal, this.flipVertical);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
