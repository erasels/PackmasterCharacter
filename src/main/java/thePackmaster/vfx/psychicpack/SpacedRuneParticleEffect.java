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

public class SpacedRuneParticleEffect extends AbstractGameEffect {
    private static final TextureAtlas runeAtlas;
    private static final Array<TextureAtlas.AtlasRegion> runes;

    static {
        runeAtlas = new TextureAtlas(Gdx.files.internal(makeImagePath("vfx/blankrune.atlas")));
        runes = runeAtlas.getRegions();
    }

    private static final float ORIGIN_VARIATION = 8f * Settings.scale;

    private static final float INIT_OFFSET_MIN = 35f * Settings.scale;
    private static final float INIT_OFFSET_MAX = 55f * Settings.scale;
    private static final float MIN_MOVEMENT = 11 * Settings.scale;
    private static final float MAX_MOVEMENT = 22 * Settings.scale;
    private static final float END_DURATION = 1.7f;
    private static final float DONE_DURATION = 2.0f;
    private static final float FADE_TIME = DONE_DURATION - END_DURATION;

    private float x, ix, tx;
    private float y, iy, ty;
    private final float midDuration;
    private final float targetScale;
    private final int runeIndex;

    public SpacedRuneParticleEffect(float x, float y, float scale)
    {
        x += MathUtils.random(-ORIGIN_VARIATION, ORIGIN_VARIATION);
        y += MathUtils.random(-ORIGIN_VARIATION, ORIGIN_VARIATION);

        float distance = MathUtils.random(INIT_OFFSET_MIN, INIT_OFFSET_MAX);
        float direction = MathUtils.random(-180.0f, 180.0f);
        float xOffset = MathUtils.cos(direction) * distance;
        float yOffset = MathUtils.sin(direction) * distance;

        this.x = this.ix = x - 15 + xOffset;
        this.y = this.iy = y - 15 + yOffset;

        distance = MathUtils.random(MIN_MOVEMENT, MAX_MOVEMENT);
        xOffset = MathUtils.cos(direction) * distance;
        yOffset = MathUtils.sin(direction) * distance;

        this.tx = this.x + xOffset;
        this.ty = this.y + yOffset;
        this.scale = 0;
        this.targetScale = Math.max(scale, 0.5f) * MathUtils.random(0.6f, 1.0f);

        this.duration = 0;
        this.rotation = MathUtils.random(-10, 10);
        this.runeIndex = MathUtils.random(runes.size - 1);

        this.midDuration = MathUtils.random(0.6f, 1.0f);

        int prio = MathUtils.random(2);
        this.color = new Color(prio == 0 ? 1 : MathUtils.random(0.4f, 1.0f),
                prio == 1 ? 1 : MathUtils.random(0.4f, 1.0f),
                prio == 2 ? 1 : MathUtils.random(0.4f, 1.0f), 0);
    }

    @Override
    public void update() {
        this.duration += Gdx.graphics.getDeltaTime();

        this.x = Interpolation.linear.apply(ix, tx, duration / DONE_DURATION);
        this.y = Interpolation.linear.apply(iy, ty, duration / DONE_DURATION);

        if (duration < midDuration)
        {
            this.scale = Interpolation.circleOut.apply(0, targetScale, duration / midDuration);
            this.color.a = Interpolation.pow2Out.apply(0, 1, duration / midDuration);
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
