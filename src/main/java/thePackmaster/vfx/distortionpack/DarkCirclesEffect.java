package thePackmaster.vfx.distortionpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class DarkCirclesEffect extends AbstractGameEffect {
    private static final float RING_SPACING = 0.05f;
    private static final float RING_FADE = 0.04f;
    private static final float RING_DURATION = 0.12f;

    private final TextureAtlas.AtlasRegion r;
    private final float cx, cy, xoff, yoff;
    private final int amount;
    private final float end;

    private final float[] ringScale;
    private final float[] ringAlpha;

    private final Color c;

    public DarkCirclesEffect(AbstractCreature source, int amount) {
        this.r = ImageMaster.WHITE_RING;
        this.xoff = r.originalWidth / 2f;
        this.yoff = r.originalHeight / 2f;
        this.cx = source.hb.cX;
        this.cy = source.hb.cY;
        this.amount = amount;

        ringScale = new float[amount];
        ringAlpha = new float[amount];

        for (int i = 0; i < amount; ++i) {
            ringScale[i] = (float) Math.pow(1.22, i);
            ringAlpha[i] = 0;
        }

        c = Color.BLACK.cpy();

        duration = 0;
        end = (amount - 1) * RING_SPACING + RING_DURATION;
    }

    @Override
    public void update() {
        duration += Gdx.graphics.getDeltaTime();

        float ringTime;
        for (int i = 0; i < amount; ++i) {
            ringTime = duration - (RING_SPACING * i);
            if (ringTime <= 0 || ringTime > RING_DURATION)
                ringAlpha[i] = 0;
            else if (ringTime <= RING_FADE) {
                ringAlpha[i] = Interpolation.sineOut.apply(0, 1, ringTime / RING_FADE);
            }
            else if (ringTime <= RING_DURATION) {
                ringAlpha[i] = Interpolation.circleIn.apply(1, 0, (ringTime - RING_FADE) / (RING_DURATION - RING_FADE));
            }
        }

        isDone = duration > end;
    }

    @Override
    public void render(SpriteBatch sb) {
        for (int i = 0; i < amount; ++i) {
            c.a = ringAlpha[i];
            if (c.a >= 0) {
                sb.setColor(c);
                sb.draw(r, cx - xoff, cy - yoff, xoff, yoff, r.originalWidth, r.originalHeight, ringScale[i], ringScale[i], 0);
            }
        }
    }

    @Override
    public void dispose() {

    }
}
