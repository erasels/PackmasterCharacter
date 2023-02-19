
package thePackmaster.vfx.coresetpack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeDur;
import com.megacrit.cardcrawl.helpers.ScreenShake.ShakeIntensity;
import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;

public class UpgradeShineEffectSingleHit extends AbstractGameEffect {
    private float x;
    private float y;

    private Random rng = new Random();
    public UpgradeShineEffectSingleHit(float x, float y) {
        this.x = x;
        this.y = y;
        this.duration = 0.2F;
    }

    public void update() {
            CardCrawlGame.sound.play("UpgradesPack_ShortUpgrade");
            clank(this.x - (rng.random(-60F,60F)) * Settings.scale, this.y + (rng.random(-90F,90F) * Settings.scale));
            this.isDone = true;
    }

    private void clank(float x, float y) {
        AbstractDungeon.topLevelEffectsQueue.add(new UpgradeHammerImprintEffect(x, y));
        if (!Settings.DISABLE_EFFECTS) {
            for(int i = 0; i < 30; ++i) {
                AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y + MathUtils.random(-10.0F, 10.0F) * Settings.scale));
            }

        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
