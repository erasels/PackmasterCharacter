//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package thePackmaster.effects.monsterhunterpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.GiantFireEffect;

public class ScreenOnFireEffectButDoesntCrash extends AbstractGameEffect {
    private float timer = 0.0F;
    private static final float INTERVAL = 0.05F;

    public ScreenOnFireEffectButDoesntCrash() {
        this.duration = 1.0F;
        this.startingDuration = this.duration;
    }

    public void update() {
        if (this.duration == this.startingDuration) {
            CardCrawlGame.sound.play("GHOST_FLAMES");
            AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(Color.FIREBRICK));
        }

        this.duration -= Gdx.graphics.getDeltaTime();
        this.timer -= Gdx.graphics.getDeltaTime();
        if (this.timer < 0.0F) {
            if (!AbstractDungeon.getCurrRoom().isBattleOver && !AbstractDungeon.getCurrRoom().isBattleEnding()) {
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
                AbstractDungeon.effectsQueue.add(new GiantFireEffect());
            }
            this.timer = 0.05F;
        }

        if (this.duration < 0.0F) {
            this.isDone = true;
        }

    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}