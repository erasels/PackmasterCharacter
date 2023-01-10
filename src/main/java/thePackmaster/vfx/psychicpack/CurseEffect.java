package thePackmaster.vfx.psychicpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.BorderLongFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.RoomTintEffect;

public class CurseEffect extends AbstractGameEffect {
    @SpirePatch(
            clz = AbstractCreature.class,
            method = SpirePatch.CLASS
    )
    public static class Field {
        public static SpireField<Integer> curseCount = new SpireField<>(()->0);
    }

    private static final Color ANGRY = new Color(0.3f, 0.0f, 0.1f, 1.0f);

    private AbstractCreature target;
    private int count;
    private float stakeTimer = 0.0F;
    private boolean first;

    public CurseEffect(AbstractCreature target) {
        this.target = target;
        this.count = Field.curseCount.get(target);
        if (count < 3)
            ++count;
        else
            count += 2;
        Field.curseCount.set(target, count);
        first = true;
    }

    public void update() {
        this.stakeTimer -= Gdx.graphics.getDeltaTime();
        if (first) {
            first = false;
            if (count <= 3) {
                CardCrawlGame.sound.playA("APPEAR", -0.2F);
            }
            else {
                CardCrawlGame.sound.playA("STANCE_ENTER_WRATH", -0.4F);
            }

            AbstractDungeon.effectsQueue.add(new RoomTintEffect(ANGRY.cpy(), Math.min(0.8F, count * 0.1F)));
            if (count > 3) {
                AbstractDungeon.effectsQueue.add(new BorderLongFlashEffect(new Color(0.6F, 0.0F, 0.25F, Math.min(0.5F, (count - 3) * 0.1F))));
            }
        }
        while (this.stakeTimer < 0.0F) {
            AbstractDungeon.effectsQueue.add(new CurseStakeEffect(target.hb.cX + MathUtils.random(-20.0F, 20.0F) * Settings.scale, target.hb.cY + MathUtils.random(-20.0F, 20.0F) * Settings.scale));
            this.stakeTimer += Settings.FAST_MODE ? 0.05F : 0.1F;
            --this.count;
            if (this.count == 0) {
                this.isDone = true;
                return;
            }
        }
    }

    public void render(SpriteBatch sb) {
    }

    public void dispose() {
    }
}
