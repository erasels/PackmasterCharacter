package thePackmaster.effects.showmanpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

public class MagicCylinderEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private float vX;
    private float vY;
    private static final float DUR = 2.0F;
    private float holdDur;
    private static Texture img;
    private boolean playedSfx = false;

    public MagicCylinderEffect(float x, float y, float vX, float vY, float dur) {

        img = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("vfx/MagicCylinder.png"));
        this.x = x;
        this.y = y;
        this.vX = vX;
        this.vY = vY;
        this.color = Color.WHITE.cpy();
        this.duration = dur;
        this.holdDur = dur;
        this.startingDuration = dur;
    }

    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration > 0f) {
            this.color.a = Interpolation.linear.apply(0.0F, 1.0F, (this.startingDuration - this.duration) / this.startingDuration);
            this.x = Interpolation.pow2In.apply(x, vX, this.startingDuration - this.duration);
            this.y = Interpolation.pow2In.apply(y, vY, this.startingDuration - this.duration);
        }
        else if (this.duration < -(holdDur/2)){
            this.color.a = Interpolation.linear.apply(1F, 0F, -duration / (this.holdDur/2));
        }

        if (this.duration <= -holdDur) {
            System.out.println("done");
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);
        sb.setColor(1F, 1F, 1F, this.color.a);
        sb.draw(img, (this.x - (img.getWidth() / 2F)), (this.y - (img.getHeight() / 2F)), img.getWidth() / 2F, img.getHeight() / 2F, img.getWidth(), img.getHeight(), Settings.scale, Settings.scale, this.rotation, 0, 0, img.getWidth(), img.getHeight(), false, false);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }
}
