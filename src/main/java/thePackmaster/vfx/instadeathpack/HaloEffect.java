package thePackmaster.vfx.instadeathpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class HaloEffect extends AbstractGameEffect {
    private static final Texture t = TexLoader.getTexture(makeImagePath("vfx/halo.png"));
    private static final int xOff = t.getWidth() / 2;
    private static final int yOff = t.getHeight() / 2;
    private static final float SCALE = 0.7F * Settings.scale;
    private static final float MAX_SCALE = SCALE * 1.1f;

    private final float x, y;

    public HaloEffect(float x, float y, Color c) {
        this.x = x - xOff + MathUtils.random(-1f, 1f);
        this.y = y - yOff + MathUtils.random(-1f, 1f);
        this.color = c.cpy();
        this.color.a = 0;

        this.scale = SCALE;
        this.duration = 0;
    }

    @Override
    public void update() {
        this.duration += Gdx.graphics.getDeltaTime();
        if (this.duration < 0.4f) {
            this.color.a = MathUtils.lerp(0, 0.5f, duration / 0.4f);
        }
        else if (this.duration < 1f) {
            this.color.a = 0.4f;
        }
        else {
            this.color.a = MathUtils.lerp(0.4f, 0f, (duration - 1f) / 1.2f);
        }

        if (this.duration > 0.2f) {
            this.scale = MathUtils.lerp(SCALE, MAX_SCALE, (this.duration - 0.2f) / 2f);
        }

        if (this.duration >= 2.2f)
            this.isDone = true;
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.color);
        sb.draw(t, this.x, this.y, xOff, yOff, t.getWidth(), t.getHeight(), this.scale, this.scale, this.rotation, 0, 0, t.getWidth(), t.getHeight(), false, false);
        sb.setBlendFunction(770, 1);
        sb.draw(t, this.x, this.y, xOff, yOff, t.getWidth(), t.getHeight(), this.scale, this.scale, this.rotation, 0, 0, t.getWidth(), t.getHeight(), false, false);
        sb.setBlendFunction(770, 771);
    }

    @Override
    public void dispose() {

    }
}
