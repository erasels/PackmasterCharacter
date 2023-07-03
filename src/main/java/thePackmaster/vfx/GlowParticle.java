package thePackmaster.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class GlowParticle extends AbstractGameEffect {
    private static final float DURATION = 3f;
    private float scale = 0.01f, maxScale;
    private Texture img;
    private float x, y;

    public GlowParticle(Texture img, float x, float y, float angle, float maxScale) {
        this.duration = DURATION;
        this.img = img;
        this.x = x;
        this.y = y;
        this.rotation = angle;
        this.maxScale = maxScale;
        color = Color.WHITE.cpy();
    }

    public GlowParticle(Texture img, float x, float y) {
        this(img, x, y, 0, 2f);
    }

    @Override
    public void update() {
        duration -= Gdx.graphics.getDeltaTime();
        scale = Interpolation.fade.apply(1f, maxScale, 1f - duration / DURATION);
        color.a = Interpolation.fade.apply(1f, 0f, 1f - duration / DURATION) / 2f;

        if (duration < 0f) {
            isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(GL30.GL_SRC_ALPHA, GL30.GL_ONE); // Additive Mode
        sb.setColor(color);
        float imageWidth = img.getWidth() * Settings.scale, imageHeight = img.getHeight() * Settings.scale;
        sb.draw(
                img,
                x - imageWidth / 2f,
                y - imageHeight / 2f,
                imageWidth / 2f,
                imageHeight / 2f,
                imageWidth,
                imageHeight,
                scale,
                scale,
                rotation,
                0,
                0,
                img.getWidth(),
                img.getHeight(),
                false,
                false
        );
        sb.setBlendFunction(GL30.GL_SRC_ALPHA, GL30.GL_ONE_MINUS_SRC_ALPHA); // NORMAL
    }

    @Override
    public void dispose() {
    }
}
