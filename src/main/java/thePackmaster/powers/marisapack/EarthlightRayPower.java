package thePackmaster.powers.marisapack;

import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EarthlightRayPower extends AbstractPackmasterPower implements CloneablePowerInterface, OnReceivePowerPower {
    public static final String POWER_ID = makeID(EarthlightRayPower.class.getSimpleName().replace("Power", ""));
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public EarthlightRayPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, Wiz.p(), amount);
    }

    @Override
    public boolean onReceivePower(AbstractPower power, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if(ChargeUpPower.POWER_ID.equals(power.ID)) {
            int cur = 0;
            AbstractPower curCharge = owner.getPower(ChargeUpPower.POWER_ID);
            if(curCharge != null) {
                cur = curCharge.amount;
            }
            int finalCur = cur;
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    EarthlightRayPower.this.flash();
                    int[] arr = DamageInfo.createDamageMatrix((power.amount + finalCur)*EarthlightRayPower.this.amount, true);
                    Wiz.att(new DamageAllEnemiesAction(owner,
                            arr,
                            DamageInfo.DamageType.THORNS,
                            AbstractGameAction.AttackEffect.FIRE,
                            true));
                    isDone = true;
                }
            });
        }
        return true;
    }

    @Override
    public AbstractPower makeCopy() {
        return new EarthlightRayPower(amount);
    }

    @Override
    public void updateDescription() {
        if(amount > 1) {
            description = String.format(DESCRIPTIONS[1], amount);
        } else {
            description = DESCRIPTIONS[0];
        }
    }
}




