package thePackmaster.vfx.distortionpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class PixelEffect extends AbstractGameEffect {
    private static final Texture pixel = TexLoader.getTexture(makeImagePath("vfx/pix.png"));

    private float xOff, yOff;
    private float setX = 0, setY = 0;
    private final int width, height;

    public PixelEffect(boolean black, float range, int size) {
        duration = MathUtils.random(0.05f, 0.25f);

        this.color = black ? Color.BLACK.cpy() : Color.WHITE.cpy();

        xOff = MathUtils.random(-range, range);
        yOff = MathUtils.random(-range, range);

        if (MathUtils.randomBoolean(0.6f))
            width = 1;
        else
            width = MathUtils.random(1, size);

        if (width > 1 || MathUtils.randomBoolean(0.4f))
            height = 1;
        else
            height = MathUtils.random(1, size);

        if (xOff > 0)
            xOff -= width;

        if (yOff > 0)
            yOff -= height;
    }

    public PixelEffect setPos(float x, float y) {
        this.setX = x;
        this.setY = y;
        return this;
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        if (this.duration <= 0.0f) {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch sb, float x, float y) {
        sb.setColor(color);
        sb.draw(pixel, x + xOff, y + yOff, width, height);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(pixel, setX + xOff, setY + yOff, width, height);
    }

    @Override
    public void dispose() {

    }
}