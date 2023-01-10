package thePackmaster.vfx.summonspack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.summonspack.Louse;

import static java.lang.Math.pow;
import static thePackmaster.orbs.summonspack.Louse.BOUNCE_DURATION;
import static thePackmaster.orbs.summonspack.Louse.GRAVITY;

public class LouseExitEffect extends AbstractGameEffect {
    private final Louse louse;
    private float peakTime;
    private float peakY;
    private final float sourceX;
    private final float sourceY;
    private final float targetX;
    private final float targetY;
    private float bounceTime = 0;

    public LouseExitEffect(Louse louse) {
        this.louse = louse;
        sourceX = louse.cX;
        sourceY = louse.cY;
        targetX = Settings.WIDTH * MathUtils.random(1.1f, 1.7f);
        targetY = Settings.HEIGHT * MathUtils.random(-0.6f, 1.2f);
        calculatePeak();
    }

    private void calculatePeak() {
        peakTime = (targetY - sourceY) / BOUNCE_DURATION / GRAVITY + BOUNCE_DURATION / 2;
        peakY = GRAVITY * peakTime * peakTime / 2.0f + louse.cY;
    }

    @Override
    public void update() {
        float rot = ReflectionHacks.getPrivate(louse, Louse.class, "rotation");
        rot += 1.0f*Gdx.graphics.getDeltaTime()*360.0f;
        ReflectionHacks.setPrivate(louse, Louse.class, "rotation", rot);

        bounceTime += Gdx.graphics.getDeltaTime();
        louse.cX = sourceX + (targetX - sourceX) * (bounceTime / BOUNCE_DURATION);
        louse.cY = (float)(peakY - pow(bounceTime - peakTime, 2)*GRAVITY/2.0f);
        if (bounceTime > BOUNCE_DURATION) {
            isDone = true;
            SpireAnniversary5Mod.louseList.remove(louse);
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {
    }

    @Override
    public void dispose() {
    }
}