package thePackmaster.actions.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.powers.rippack.DividedFuryPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.rippack.DividedFuryEffect;

import java.util.ArrayList;

import static thePackmaster.util.Wiz.att;

public class DividedFuryAction extends AbstractGameAction {

    AbstractCard card;
    public DividedFuryAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        AbstractPower pow = AbstractDungeon.player.getPower(DividedFuryPower.POWER_ID);
        if(pow != null) {
            int amount = pow.amount;
            pow.flash();
            if (card.type == AbstractCard.CardType.SKILL) {
                att(new GainBlockAction(AbstractDungeon.player, amount));

            } else if (card.type == AbstractCard.CardType.ATTACK) {
                //OK hear me out:
                //Need to single out a one spear to wait for to complete for the timing of damage
                //So get a monster, and apply the spear effect to the monster
                //Then loop and do the rest of the monsters' spears
                //Once the singled out one is done, do the damage
                //att because we're in an action
                //I don't like it either
                ArrayList<AbstractMonster> livingMonsters = Wiz.getEnemies();
                if (livingMonsters.size() > 0) {
                    AbstractMonster firstMon = livingMonsters.get(0);
                    AbstractGameEffect spear = Settings.FAST_MODE ? DividedFuryEffect.LightningSpearThrowFast(firstMon, false) : DividedFuryEffect.LightningSpearThrow(firstMon, false);
                    att(new AbstractGameAction() {
                        @Override
                        public void update() {
                            if (spear.isDone) {
                                isDone = true;
                                AbstractPower pow = AbstractDungeon.player.getPower(DividedFuryPower.POWER_ID);
                                att(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(pow.amount, true), DamageInfo.DamageType.THORNS, AttackEffect.NONE));
                            }
                        }
                    });
                    att(new VFXAction(spear));
                    for (AbstractMonster m : livingMonsters) {
                        if (m != firstMon) {
                            AbstractGameEffect otherSpear = Settings.FAST_MODE ? DividedFuryEffect.LightningSpearThrowFast(m, true) : DividedFuryEffect.LightningSpearThrow(m, true);
                            att(new VFXAction(otherSpear));
                        }
                    }

                }
            }
        }
        isDone = true;
    }
}
