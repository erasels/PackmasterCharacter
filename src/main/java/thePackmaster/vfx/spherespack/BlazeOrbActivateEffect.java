package thePackmaster.vfx.spherespack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class BlazeOrbActivateEffect extends AbstractGameEffect {
    private float x;
    private float y;

    public BlazeOrbActivateEffect(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        for(int i = 0; i < 4; ++i) {
            AbstractDungeon.effectsQueue.add(new BlazeOrbActivateParticle(this.x, this.y));
        }

        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
