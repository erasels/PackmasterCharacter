package thePackmaster.vfx.arcanapack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import java.util.Iterator;

public class SunEffect extends AbstractGameEffect {
    float delay = 0;
    public static final float DELAY_TIME = 0.03f;

    Iterator<AbstractMonster> enemyIterator;
    AbstractMonster next;

    boolean first = true;

    public SunEffect() {
        enemyIterator = AbstractDungeon.getMonsters().monsters.iterator();
        while (next == null && enemyIterator.hasNext()) {
            next = enemyIterator.next();
            if (next.isDeadOrEscaped())
                next = null;
        }
    }

    @Override
    public void update() {
        if (next == null) {
            isDone = true;
            return;
        }

        if (first) {
            first = false;
            CardCrawlGame.sound.playA("ATTACK_FLAME_BARRIER", 0.25F);
        }

        delay -= Gdx.graphics.getDeltaTime();
        if (delay <= 0) {
            delay += DELAY_TIME;
            AbstractDungeon.effectsQueue.add(new SunBeamEffect(next));

            next = null;
            while (next == null && enemyIterator.hasNext()) {
                next = enemyIterator.next();
                if (next.isDeadOrEscaped())
                    next = null;
            }
        }
    }

    @Override
    public void render(SpriteBatch spriteBatch) {

    }

    @Override
    public void dispose() {

    }
}
