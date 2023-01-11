package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class DarkBounceEffect extends AbstractGameEffect {
    private Texture img;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float floor;
    private boolean flipX, flipY;
    private static final float GRAVITY;

    public DarkBounceEffect(float x, float y) {
        float rand = MathUtils.random();
        if (rand < 0.33f) {
            this.img = ImageMaster.DARK_ORB_PASSIVE_VFX_1;
        }
        else if (rand < 0.667f) {
            this.img = ImageMaster.DARK_ORB_PASSIVE_VFX_2;
        }
        else {
            this.img = ImageMaster.DARK_ORB_PASSIVE_VFX_3;
        }

        flipX = MathUtils.randomBoolean();
        flipY = MathUtils.randomBoolean();

        this.duration = MathUtils.random(0.5F, 1.0F);
        this.x = x - (float) (this.img.getWidth() / 2);
        this.y = y - (float) (this.img.getHeight() / 2);
        this.color = new Color(MathUtils.random(0.75F, 1.0F), MathUtils.random(0.7F, 1.0F), MathUtils.random(0.8F, 1.0F), 0.0F);
        this.color.a = 0.0F;
        this.rotation = MathUtils.random(0.0F, 360.0F);
        this.scale = MathUtils.random(0.5F, 2.0F) * Settings.scale;
        this.vX = MathUtils.random(-900.0F, 900.0F) * Settings.scale;
        this.vY = MathUtils.random(500.0F, 1000.0F) * Settings.scale;
        this.floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
    }// 42

    public void update() {
        this.vY -= GRAVITY / this.scale * Gdx.graphics.getDeltaTime();
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        Vector2 test = new Vector2(this.vX, this.vY);
        this.rotation = test.angle();
        if (this.y < this.floor) {
            this.vY = -this.vY * 0.75F;
            this.y = this.floor + 0.1F;
            this.vX *= 1.1F;
        }

        if (1.0F - this.duration < 0.1F) {
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (1.0F - this.duration) * 10.0F);
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);

        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float) this.img.getWidth() / 2.0F, (float) this.img.getHeight() / 2.0F, (float) this.img.getWidth(), (float) this.img.getHeight(), this.scale * MathUtils.random(0.8F, 1.2F), this.scale * MathUtils.random(0.8F, 1.2F), this.rotation, 0, 0, img.getWidth(), img.getHeight(), flipX, flipY);
        sb.draw(this.img, this.x, this.y, (float) this.img.getWidth() / 2.0F, (float) this.img.getHeight() / 2.0F, (float) this.img.getWidth(), (float) this.img.getHeight(), this.scale * MathUtils.random(0.8F, 1.2F), this.scale * MathUtils.random(0.8F, 1.2F), this.rotation, 0, 0, img.getWidth(), img.getHeight(), flipX, flipY);
        sb.setBlendFunction(770, 771);
    }

    public void dispose() {
    }

    static {
        GRAVITY = 3000.0F * Settings.scale;
    }
}