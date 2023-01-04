package thePackmaster.vfx.bardinspirepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class LifeDrainEffect extends AbstractGameEffect
{
    private float x;
    private float y;
    private float tX;
    private float tY;

    public LifeDrainEffect(float sX, float sY, float tX, float tY)
    {
        x = sX;
        y = sY;
        this.tX = tX;
        this.tY = tY;

        scale = 0.12f;
        duration = 0.5f;
    }

    @Override
    public void update()
    {
        scale -= Gdx.graphics.getDeltaTime();
        if (scale < 0) {
            AbstractDungeon.effectsQueue.add(new LifeDrainParticle(
                    x,
                    y,
                    tX + MathUtils.random(60f, -60f) * Settings.scale,
                    tY + MathUtils.random(60f, -60f) * Settings.scale,
                    AbstractDungeon.player.flipHorizontal
            ));
            scale = 0.04f;
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0) {
            isDone = true;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch)
    {
    }

    @Override
    public void dispose()
    {
    }
}
