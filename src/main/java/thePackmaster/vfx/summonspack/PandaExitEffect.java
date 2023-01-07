package thePackmaster.vfx.summonspack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.summonspack.Panda;

import static java.lang.Math.pow;
import static thePackmaster.orbs.summonspack.Panda.BOUNCE_DURATION;
import static thePackmaster.orbs.summonspack.Panda.GRAVITY;

public class PandaExitEffect extends AbstractGameEffect {
    private final Panda panda;
    private float peakTime;
    private float peakY;
    private final float sourceX;
    private final float sourceY;
    private final float targetX;
    private final float targetY;
    private float bounceTime = 0;

    public PandaExitEffect(Panda panda) {
        this.panda = panda;
        sourceX = panda.cX;
        sourceY = panda.cY;
        targetX = Settings.WIDTH * MathUtils.random(1.1f, 1.7f);
        targetY = Settings.HEIGHT * MathUtils.random(-0.6f, 1.2f);
        calculatePeak();
    }

    private void calculatePeak() {
        peakTime = (targetY - sourceY) / BOUNCE_DURATION / GRAVITY + BOUNCE_DURATION / 2;
        peakY = GRAVITY * peakTime * peakTime / 2.0f + panda.cY;
    }

    @Override
    public void update() {
        float rot = ReflectionHacks.getPrivate(panda, Panda.class, "rotation");
        rot += 1.0f*Gdx.graphics.getDeltaTime()*360.0f;
        ReflectionHacks.setPrivate(panda, Panda.class, "rotation", rot);

        bounceTime += Gdx.graphics.getDeltaTime();
        panda.cX = sourceX + (targetX - sourceX) * (bounceTime / BOUNCE_DURATION);
        panda.cY = (float)(peakY - pow(bounceTime - peakTime, 2)*GRAVITY/2.0f);
        if (bounceTime > BOUNCE_DURATION) {
            isDone = true;
            SpireAnniversary5Mod.pandaList.remove(panda);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    }

    @Override
    public void dispose() {
    }
}
