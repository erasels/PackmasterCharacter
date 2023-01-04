package thePackmaster.vfx.distortionpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class StaticEffect extends AbstractGameEffect {
    private final AbstractCreature target;
    private final int amt;
    private int added = 0;
    private float delay = 0;

    public StaticEffect(AbstractCreature target, int amt) {
        this.target = target;
        this.amt = amt;
    }

    @Override
    public void update() {
        delay -= Gdx.graphics.getDeltaTime();
        if (delay <= 0) {
            while (added < amt && MathUtils.randomBoolean(0.82f)) {
                AbstractDungeon.effectsQueue.add(new PixelEffect(MathUtils.randomBoolean(), target.hb.width / 3, Math.min(16, amt)));
                ++added;
            }
            if (added >= amt)
                isDone = true;
            delay = 0.02f;
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
