package thePackmaster.vfx.aggressionpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ColoredGhostlyFireEffect extends AbstractGameEffect {
    private final AtlasRegion img = this.getImg();
    private float x;
    private float y;
    private final float vX;
    private final float vY;
    private final boolean transparent;
    private static final float DURATION = 1.0F;

    public ColoredGhostlyFireEffect(float x, float y, Color c, boolean transparent) {
        this.x = x + MathUtils.random(-2.0F, 2.0F) * Settings.scale - (float)this.img.packedWidth / 2.0F;
        this.y = y + MathUtils.random(-2.0F, 2.0F) * Settings.scale - (float)this.img.packedHeight / 2.0F;
        this.vX = MathUtils.random(-10.0F, 10.0F) * Settings.scale;
        this.vY = MathUtils.random(20.0F, 150.0F) * Settings.scale;
        // Without transparency, the flames linger behind the wandering fire for longer than we with transparency, since
        // the normal behavior is for this effect to steadily fade using transparency. Therefore, to keep a similar feel
        // between transparency and non-transparent modes, we shorten the duration of the flames.
        this.duration = transparent ? DURATION : DURATION * 0.6F;
        this.color = c.cpy();
        if (transparent) {
            this.color.a = 0.0F;
        }
        this.transparent = transparent;
        this.scale = Settings.scale * MathUtils.random(5.0F, 6.0F);
    }

    private AtlasRegion getImg() {
        switch(MathUtils.random(0, 2)) {
        case 0:
            return ImageMaster.TORCH_FIRE_1;
        case 1:
            return ImageMaster.TORCH_FIRE_2;
        default:
            return ImageMaster.TORCH_FIRE_3;
        }
    }

    public void update() {
        this.x += this.vX * Gdx.graphics.getDeltaTime();
        this.y += this.vY * Gdx.graphics.getDeltaTime();
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration < 0.0F) {
            this.isDone = true;
        }

        if (this.scale > 0.1F) {
            this.scale -= Gdx.graphics.getDeltaTime() / 4.0F;
        }

        if (this.transparent) {
            this.color.a = this.duration / 2.0F;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, this.transparent ? GL20.GL_ONE : GL20.GL_ONE_MINUS_SRC_ALPHA);
        sb.setColor(this.color);
        sb.draw(this.img, this.x, this.y, (float)this.img.packedWidth / 2.0F, (float)this.img.packedHeight / 2.0F, (float)this.img.packedWidth, (float)this.img.packedHeight, this.scale * MathUtils.random(0.95F, 1.05F), this.scale * MathUtils.random(0.95F, 1.05F), this.rotation);
        sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public void dispose() {
    }
}
