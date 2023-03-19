package thePackmaster.actions.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.bitingcoldpack.SnowedInPower;
import thePackmaster.util.Wiz;

import static thePackmaster.util.Wiz.atb;

public class FrostbiteDamageAction extends AbstractGameAction {
    private final int damage;
    private final AbstractPower callingPower;
    private final boolean shouldReduce;

    public FrostbiteDamageAction(AbstractCreature target, AbstractPower callingPower, boolean shouldReduce) {
        this.target = target;
        this.damage = callingPower.amount;
        this.callingPower = callingPower;
        this.shouldReduce = shouldReduce;
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
    }

    public FrostbiteDamageAction(AbstractCreature target, AbstractPower callingPower) {
        this(target, callingPower, true);
    }

    @Override
    public void update() {
        if(shouldCancelAction()) {
            this.isDone = true;
        } else {
            tickDuration();
            if (this.isDone) {

                callingPower.flashWithoutSound();
                CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.05F);
                this.target.damage(new DamageInfo(Wiz.p(), damage, DamageInfo.DamageType.HP_LOSS));

                // Reduction of Frostbite
                // includes Snowed In check
                AbstractPower snowedIn = target.getPower(SnowedInPower.POWER_ID);
                if (snowedIn != null) {
                    atb(new AbstractGameAction() {
                        @Override
                        public void update() {
                            snowedIn.flash();
                            this.isDone = true;
                        }
                    });
                } else if (shouldReduce) {
                    atb(new ReducePowerAction(target, AbstractDungeon.player, callingPower,
                            (callingPower.amount / 2) + (callingPower.amount % 2)
                    ));
                }
            }
        }
    }
}