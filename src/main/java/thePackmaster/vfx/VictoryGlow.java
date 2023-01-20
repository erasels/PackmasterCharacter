package thePackmaster.vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

public class VictoryGlow extends AbstractGameEffect {

    private float xscale;
    private float yscale;

    private float x;
    private float y;

    private float flip_speed;
    private float flip_counter;

    private static Texture texture = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("vfx/EndingGlow.png"));

    public static float alpha;

    public VictoryGlow() {
        this.renderBehind = true;
        alpha = 0.0F;
        this.xscale = 1f * Settings.WIDTH;
        this.yscale = 1f * Settings.yScale;
        this.x = 0;
        this.y = 0;
        this.flip_speed = 0.55F;
        this.flip_counter = MathUtils.random(0.0F, 6.4F);
    }

    @Override
    public void render(SpriteBatch sb) {
        this.color = new Color(1, 1, 1, alpha);
        sb.setColor(this.color);

        int w = texture.getWidth();
        int h = texture.getHeight();
        sb.draw(texture, x, y,
                0, 0,
                w, h,
                this.xscale, this.yscale,
                0,
                0, 0,
                w, h,
                false, false);
    }

    @Override
    public void dispose() {
        // throw away the potato
    }

    @Override
    public void update() {
        final float dt = Gdx.graphics.getDeltaTime();

        alpha += 0.25F * dt;
        if (alpha > 1.0F)
            alpha = 1.0F;

        this.flip_counter += this.flip_speed * dt;
        this.yscale = 0.825F + MathUtils.sin(this.flip_counter)*0.175F;
    }
}

