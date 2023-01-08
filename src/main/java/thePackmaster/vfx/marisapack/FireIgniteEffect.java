package thePackmaster.vfx.marisapack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.LightFlareParticleEffect;

public class FireIgniteEffect extends AbstractGameEffect {
    private float x;
    private float y;
    private int amount;

    public FireIgniteEffect(float x, float y, int amount) {
        this.x = x;
        this.y = y;
        this.amount = amount;
    }

    public void update() {
        for (int i = 0; i < amount; ++i) {
            AbstractDungeon.effectsQueue.add(new RedFireBurstParticleEffect(this.x, this.y));
            AbstractDungeon.effectsQueue.add(new LightFlareParticleEffect(this.x, this.y, BetterFireballEffect.randomFlareColor()));
        }

        this.isDone = true;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
