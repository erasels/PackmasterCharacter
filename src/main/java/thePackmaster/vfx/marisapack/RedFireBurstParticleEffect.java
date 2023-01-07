package thePackmaster.vfx.marisapack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RedFireBurstParticleEffect extends AbstractGameEffect {
    private TextureAtlas.AtlasRegion img;
    private static final float DUR = 1.0F;
    private float x;
    private float y;
    private float vX;
    private float vY;
    private float floor;
    private static final float GRAVITY;

    public RedFireBurstParticleEffect(float x, float y) {
        int roll = MathUtils.random(0, 2);
        if (roll == 0) {
            this.img = ImageMaster.TORCH_FIRE_1;
        } else if (roll == 1) {
            this.img = ImageMaster.TORCH_FIRE_2;
        } else {
            this.img = ImageMaster.TORCH_FIRE_3;
        }

        this.duration = MathUtils.random(0.5F, DUR);
        this.x = x - (float)(this.img.packedWidth / 2);
        this.y = y - (float)(this.img.packedHeight / 2);
        this.color = new Color(MathUtils.random(0.7F, 1.0F), MathUtils.random(0.2F, 0.4F), MathUtils.random(0.1F, 0.3F), 0.0F);
        this.color.a = 0.0F;
        this.rotation = MathUtils.random(-10.0F, 10.0F);
        this.scale = MathUtils.random(2.0F, 4.0F) * Settings.scale;
        this.vX = MathUtils.random(-900.0F, 900.0F) * Settings.scale;
        this.vY = MathUtils.random(0.0F, 500.0F) * Settings.scale;
        this.floor = MathUtils.random(100.0F, 250.0F) * Settings.scale;
    }

    public void update() {
        this.vY += GRAVITY / this.scale * Gdx.graphics.getDeltaTime();// 51
        this.x += this.vX * Gdx.graphics.getDeltaTime() * MathUtils.sinDeg(Gdx.graphics.getDeltaTime());// 52
        this.y += this.vY * Gdx.graphics.getDeltaTime();// 53
        if (this.scale > 0.3F * Settings.scale) {// 54
            this.scale -= Gdx.graphics.getDeltaTime() * 2.0F;// 55
        }

        if (this.y < this.floor) {// 58
            this.vY = -this.vY * 0.75F;// 59
            this.y = this.floor + 0.1F;// 60
            this.vX *= 1.1F;// 61
        }

        if (1.0F - this.duration < 0.1F) {// 64
            this.color.a = Interpolation.fade.apply(0.0F, 1.0F, (DUR - this.duration) * 10.0F);// 65
        } else {
            this.color.a = Interpolation.pow2Out.apply(0.0F, 1.0F, this.duration);// 67
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 70
        if (this.duration < 0.0F) {// 71
            this.isDone = true;// 72
        }

    }// 74

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 1);// 78
        sb.setColor(this.color);// 79
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale, this.scale, this.rotation);// 80
        sb.setBlendFunction(770, 771);// 91
    }// 92

    public void dispose() {
    }// 97

    static {
        GRAVITY = 180.0F * Settings.scale;// 17
    }
}
