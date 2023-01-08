package thePackmaster.vfx.marisapack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;

public class BetterFireballEffect extends AbstractGameEffect {
    private static final float FIREBALL_INTERVAL = 0.016F;

    private static final float FAST_TRAVEL_TIME = 0.15f;
    private static final float TRAVEL_TIME = 0.25f;

    private float x;
    private float y;
    private float targetX;
    private float targetY;
    private float vfxTimer = 0.0F;

    private float startDistance;
    private float distance;
    private float delay;
    private float rotationMultiplier;

    public BetterFireballEffect(float cX, float cY, float distance, float angle, float movementDelay) {
        this.startingDuration = this.duration = Settings.FAST_MODE ? FAST_TRAVEL_TIME : TRAVEL_TIME;
        this.startDistance = this.distance = distance;
        this.rotation = angle;
        this.rotationMultiplier = Settings.FAST_MODE ? 4 : 3;
        this.targetX = cX + MathUtils.random(-10.0F, 10.0F) * Settings.scale;
        this.targetY = cY + MathUtils.random(-10.0F, 10.0F) * Settings.scale;

        this.delay = movementDelay;

        calculatePosition();
    }

    public void update() {
        if (this.delay > 0)
        {
            this.delay -= Gdx.graphics.getDeltaTime();
        }
        else
        {
            this.duration -= (Gdx.graphics.getDeltaTime() - this.delay);
            this.delay = 0;

            if (this.duration < 0)
                this.duration = 0;

            this.distance = Interpolation.fade.apply(0, this.startDistance, this.duration / this.startingDuration);
            this.rotation = (this.rotation + Gdx.graphics.getDeltaTime() * rotationMultiplier) % MathUtils.PI2;
            calculatePosition();
        }
        this.vfxTimer -= Gdx.graphics.getDeltaTime();
        if (this.vfxTimer <= 0.0F) {
            this.vfxTimer += FIREBALL_INTERVAL;
            AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(this.x, this.y, randomFlareColor()));
            AbstractDungeon.effectsQueue.add(new RedFireBurstParticleEffect(this.x, this.y));
        }

        if (this.duration <= 0.0F) {
            this.isDone = true;
            AbstractDungeon.effectsQueue.add(new FireIgniteEffect(this.x, this.y, 6));
            AbstractDungeon.effectsQueue.add(new CasualFlameParticleEffect(this.x, this.y));
        }
    }

    private void calculatePosition()
    {
        x = targetX + MathUtils.cos(this.rotation) * this.distance;
        y = targetY + MathUtils.sin(this.rotation) * distance;
    }

    public static Color randomFlareColor()
    {
        int i = MathUtils.random(3);
        switch (i) {
            case 0:
                return Color.ORANGE;
            case 1:
                return Color.YELLOW;
            default:
                return Color.RED;
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
