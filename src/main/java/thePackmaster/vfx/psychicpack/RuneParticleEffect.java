package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class RuneParticleEffect extends AbstractGameEffect {
    private static final TextureAtlas runeAtlas;
    private static final Array<TextureAtlas.AtlasRegion> runes;

    static {
        runeAtlas = new TextureAtlas(Gdx.files.internal(makeImagePath("vfx/rune.atlas")));
        runes = runeAtlas.getRegions();
    }

    private static final float MAX_OFFSET = 3 * Settings.scale;
    private static final float MIN_MOVEMENT = 37 * Settings.scale;
    private static final float MAX_MOVEMENT = 40 * Settings.scale;
    private static final float MID_DURATION = 1.0f;
    private static final float END_DURATION = 1.7f;
    private static final float DONE_DURATION = 2.0f;
    private static final float FADE_TIME = DONE_DURATION - END_DURATION;

    private float x, ix, tx;
    private float y, iy, ty;
    private final float targetScale;
    private final int runeIndex;

    public RuneParticleEffect(float x, float y, float scale)
    {
        this.x = this.ix = x - 15 + MathUtils.random(-MAX_OFFSET, MAX_OFFSET) * scale;
        this.y = this.iy = y - 15 + MathUtils.random(-MAX_OFFSET, MAX_OFFSET) * scale;

        float distance = MathUtils.random(MIN_MOVEMENT, MAX_MOVEMENT) * (MathUtils.randomBoolean() ? 1 : -1);
        float direction = MathUtils.random(-180.0f, 180.0f);
        float xOffset = MathUtils.cos(direction) * distance;
        float yOffset = MathUtils.sin(direction) * distance;

        this.tx = this.x + xOffset;
        this.ty = this.y + yOffset;
        this.scale = 0;
        this.targetScale = Math.max(scale, 0.5f) * MathUtils.random(0.6f, 1.0f);

        this.duration = 0;
        this.rotation = MathUtils.random(-10, 10);
        this.runeIndex = MathUtils.random(runes.size - 1);

        this.color = Color.WHITE.cpy();
    }

    @Override
    public void update() {
        this.duration += Gdx.graphics.getDeltaTime();

        this.x = Interpolation.pow2Out.apply(ix, tx, duration / DONE_DURATION);
        this.y = Interpolation.pow2Out.apply(iy, ty, duration / DONE_DURATION);

        if (duration < MID_DURATION)
        {
            this.scale = Interpolation.circleOut.apply(0, targetScale, duration / MID_DURATION);
            this.color.a = Interpolation.pow2Out.apply(0, 1, duration / MID_DURATION);
        }
        else
        {
            if (this.duration > END_DURATION)
            {
                this.color.a = Interpolation.linear.apply(1, 0, (duration - END_DURATION) / FADE_TIME);
            }

            this.scale = targetScale;

            this.isDone = this.duration > DONE_DURATION;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.draw(runes.get(runeIndex), x, y, 15, 15, 30, 30, scale, scale, 0);
    }

    @Override
    public void dispose() {

    }
}
