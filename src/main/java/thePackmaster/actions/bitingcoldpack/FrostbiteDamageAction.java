package thePackmaster.actions.bitingcoldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import thePackmaster.powers.bitingcoldpack.SnowedInPower;
import thePackmaster.relics.bitingcoldpack.Snowglobe;

import static thePackmaster.util.Wiz.atb;

public class FrostbiteDamageAction extends AbstractGameAction {
    private final DamageInfo info;
    private int damage;
    private final AbstractPower callingPower;

    public FrostbiteDamageAction(AbstractCreature target, DamageInfo info, AbstractPower callingPower) {
        this.info = info;
        this.damage = this.info.base;
        this.callingPower = callingPower;
        setValues(target, info);
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

                callingPower.flashWithoutSound();
                CardCrawlGame.sound.play("ORB_FROST_EVOKE", 0.05F);
                this.target.damage(new DamageInfo(info.owner, damage, this.info.type));

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
                } else {
                    atb(new ReducePowerAction(target, AbstractDungeon.player, callingPower,
                            this.amount % 2 != 0 ? (callingPower.amount / 2) + 1 : (callingPower.amount / 2)
                    ));
                }
            }
        }
    }
}