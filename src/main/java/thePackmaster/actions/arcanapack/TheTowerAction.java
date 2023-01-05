package thePackmaster.actions.arcanapack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class TheTowerAction extends AbstractGameAction {
    private AbstractCard c;
    private DamageInfo info;

    public TheTowerAction(AbstractCard c, AbstractCreature target, DamageInfo info) {
        this.c = c;
        this.info = info;
        this.setValues(target, info);

        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_FASTER;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FASTER && this.target != null) {
            AbstractDungeon.effectList.add(new LightningEffect(target.drawX, target.drawY));
            CardCrawlGame.sound.playA("ORB_LIGHTNING_EVOKE", -0.2f);
            this.target.damage(this.info);

            if (this.target.isDying || this.target.currentHealth <= 0) {
                AbstractCard tmp = c.makeStatEquivalentCopy();
                tmp.purgeOnUse = true;
                this.addToBot(new NewQueueCardAction(tmp, true, false, true));
            }

            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                AbstractDungeon.actionManager.clearPostCombatActions();
            }
        }

        this.tickDuration();
    }
}
