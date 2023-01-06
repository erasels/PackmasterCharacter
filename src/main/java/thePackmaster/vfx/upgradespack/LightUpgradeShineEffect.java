package thePackmaster.vfx.upgradespack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.UpgradeHammerImprintEffect;
import com.megacrit.cardcrawl.vfx.UpgradeShineParticleEffect;

public class LightUpgradeShineEffect extends AbstractGameEffect {

    private final float x;
    private final float y;
    private boolean clang1 = false;

    public LightUpgradeShineEffect(float x, float y) {
        this.x = x;// 18
        this.y = y;// 19
        this.duration = 0.8F;// 20
    }// 21

    public void update() {
        if (this.duration < 0.6F && !this.clang1) {// 24
            CardCrawlGame.sound.play("UpgradesPack_ShortUpgrade");// 25
            this.clang1 = true;// 26
            this.clank(this.x - 80.0F * Settings.scale, this.y + 0.0F * Settings.scale);
        }
        this.duration -= Gdx.graphics.getDeltaTime();
    }// 43

    private void clank(float x, float y) {
        AbstractDungeon.topLevelEffectsQueue.add(new UpgradeHammerImprintEffect(x, y));// 46
        if (!Settings.DISABLE_EFFECTS) {// 48
            for(int i = 0; i < 10; ++i) {// 52
                AbstractDungeon.topLevelEffectsQueue.add(new UpgradeShineParticleEffect(x + MathUtils.random(-10.0F, 10.0F) * Settings.scale, y + MathUtils.random(-10.0F, 10.0F) * Settings.scale));// 53 55 56
            }

        }
    }// 49 58

    public void render(SpriteBatch sb) {
    }// 63

    public void dispose() {
    }
}
