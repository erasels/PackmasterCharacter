package thePackmaster.vfx.instadeathpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class RandomReversingSlashEffect extends AbstractGameEffect {
    private final AbstractCreature target;
    private ReversingSlashEffect vfx;

    private int mode = 0;
    private Color reverseColor1 = null, reverseColor2 = null;

    private float x;
    private float y;
    private Color color2;

    private static final float FAST_SLASH_DURATION = 0.1F;

    public RandomReversingSlashEffect(AbstractCreature target, Color color1, Color color2) {
        this.target = target;
        this.x = target.hb.cX;
        this.y = target.hb.cY;
        this.color = color1.cpy();
        this.color2 = color2.cpy();
        this.startingDuration = 0.1F;
        this.duration = this.startingDuration;
    }

    public void reverse() {
        mode = 1;
    }
    public void reverse(Color color1, Color color2) {
        mode = 1;
        reverseColor1 = color1.cpy();
        reverseColor2 = color2.cpy();
    }
    public void end() {
        mode = 2;
    }

    public void update() {
        if (target.isDeadOrEscaped())
            end();

        if (vfx == null) {
            if (MathUtils.randomBoolean()) {
                if (MathUtils.randomBoolean()) {
                    CardCrawlGame.sound.playA("ATTACK_DAGGER_3", MathUtils.random(0.0F, -0.3F));
                }
                else
                {
                    CardCrawlGame.sound.playA("ATTACK_DAGGER_4", MathUtils.random(0.0F, -0.3F));
                }
            } else {
                if (MathUtils.randomBoolean()) {
                    CardCrawlGame.sound.playA("ATTACK_DAGGER_5", MathUtils.random(0.0F, -0.3F));
                }
                else
                {
                    CardCrawlGame.sound.playA("ATTACK_DAGGER_6", MathUtils.random(0.0F, -0.3F));
                }
            }

            float angle = MathUtils.random(-180.0F, 180.0F);

            float dX = MathUtils.random(90.0F, 130.0F) * (MathUtils.randomBoolean() ? 1.0F : -1.0F);
            float dY = MathUtils.random(90.0F, 130.0F) * (MathUtils.randomBoolean() ? 1.0F : -1.0F);

            float maxScale = MathUtils.random(7.0F, 8.0F);

            vfx = new ReversingSlashEffect(this.x, this.y, dX, dY, angle, maxScale, this.color, this.color2);

            vfx.startingDuration = FAST_SLASH_DURATION;
            vfx.duration = FAST_SLASH_DURATION;

            AbstractDungeon.effectsQueue.add(vfx);
        }
        else {
            if (mode == 1) {
                if (reverseColor1 != null && reverseColor2 != null) {
                    vfx.reverse(reverseColor1, reverseColor2);
                }
                else {
                    vfx.reverse();
                }
                mode = 0;
            }
            else if (mode == 2) {
                vfx.end = true;
            }
        }
        isDone = vfx.isDone;
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}