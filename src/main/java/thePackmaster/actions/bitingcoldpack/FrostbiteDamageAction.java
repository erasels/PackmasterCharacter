package thePackmaster.actions.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.powers.bitingcoldpack.ColdSeasonPower;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.relics.bitingcoldpack.Snowglobe;

import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.applyToEnemyTop;

public class FrostbiteDamageAction extends AbstractGameAction {
    private final DamageInfo info;
    private int damage;
    private final AbstractPower callingPower;
    private boolean isItColdSeason = false;

    public FrostbiteDamageAction(AbstractCreature target, DamageInfo info, AbstractPower callingPower) {
        this.info = info;
        this.damage = this.info.base;
        this.callingPower = callingPower;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if(shouldCancelAction()) {
            this.isDone = true;
        } else {
            tickDuration();
            if (this.isDone) {
                // Snowglobe
                AbstractRelic s = AbstractDungeon.player.getRelic(Snowglobe.ID);
                if (s != null) {
                    addToTop(new WaitAction(0.1F));
                    addToTop(new RelicAboveCreatureAction(info.owner, s));
                    s.flash();
                    damage += 1;
                }

                if (damage < this.info.owner.currentHealth)
                    isItColdSeason = true;

                callingPower.flashWithoutSound();
                CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.05F);
                this.target.damage(new DamageInfo(info.owner, damage, this.info.type));

                // Cold Season
                AbstractPower t = AbstractDungeon.player.getPower(ColdSeasonPower.POWER_ID);
                if (isItColdSeason && t != null) {
                    t.flash();
                    applyToEnemyTop((AbstractMonster)this.info.owner, new FrostbitePower(this.info.owner, t.amount));
                }
            }
        }
    }
}