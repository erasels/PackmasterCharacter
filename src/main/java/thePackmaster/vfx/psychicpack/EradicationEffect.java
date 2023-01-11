package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class EradicationEffect extends AbstractGameEffect {
    //normal speed: 1 second animation. .5 seconds for spawning all, .25 second pause, .25 second for them to all hit the target.
    //fast speed: 0.4 second animation. 0.1 second for spawn all (simultaneous), 0.15 second pause, 0.15 second for them to all hit target.

    private static final float DISTANCE = 200.0F * Settings.scale;

    private static final float DURATION = 1.0f;
    private static final float FAST_DURATION = 0.4f;

    private static final float ATTACK_TIME = 0.25f;
    private static final float FAST_ATTACK_TIME = 0.15f;

    private static final float SPAWN_TIME = 0.5f;
    private static final float FAST_SPAWN_TIME = 0.0f;

    private int amount;
    private int spawnedCount;
    private float x;
    private float y;

    private float spawnDelay;
    private float currentSpawnDelay;

    private float angleIncrement;

    public EradicationEffect(float targetX, float targetY, int amount)
    {
        this.duration = Settings.FAST_MODE ? FAST_DURATION : DURATION;
        this.amount = amount;
        this.spawnedCount = 0;
        this.x = targetX;
        this.y = targetY;
        currentSpawnDelay = spawnDelay = (Settings.FAST_MODE ? FAST_SPAWN_TIME : SPAWN_TIME) / (float)amount;
        this.rotation = MathUtils.random(MathUtils.PI2);
        this.angleIncrement = MathUtils.PI2 / (float)amount;
    }

    @Override
    public void update() {
        this.duration -= Gdx.graphics.getDeltaTime();
        this.currentSpawnDelay -= Gdx.graphics.getDeltaTime();

        while (this.currentSpawnDelay <= 0.0f && this.spawnedCount < this.amount)
        {
            AbstractDungeon.effectsQueue.add(new EradicationFireballEffect(this.x, this.y, DISTANCE, this.rotation, this.duration - (Settings.FAST_MODE ? FAST_ATTACK_TIME : ATTACK_TIME)));
            this.rotation += this.angleIncrement;
            this.currentSpawnDelay += spawnDelay;
            this.spawnedCount++;
        }

        if (this.duration <= 0)
        {
            this.isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
