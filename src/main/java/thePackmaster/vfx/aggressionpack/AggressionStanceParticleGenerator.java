package thePackmaster.vfx.aggressionpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.stance.DivinityStanceChangeParticle;

public class AggressionStanceParticleGenerator extends AbstractGameEffect {
    private static final int PARTICLES = 20;
    private final float x;
    private final float y;

    public AggressionStanceParticleGenerator(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        for(int i = 0; i < PARTICLES; ++i) {
            AbstractDungeon.effectsQueue.add(this.getParticleEffect());
        }
        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }

    private AbstractGameEffect getParticleEffect() {
        return new DivinityStanceChangeParticle(Color.FIREBRICK, this.x, this.y);
    }
}
