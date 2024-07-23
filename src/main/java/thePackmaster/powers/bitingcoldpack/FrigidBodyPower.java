package thePackmaster.powers.bitingcoldpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class FrigidBodyPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("FrigidBodyPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public FrigidBodyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            if (damageAmount == 0) {
                this.flash();
                att(new ApplyPowerAction(info.owner, this.owner, new FrostbitePower(info.owner, info.output), info.output));
            } else if (damageAmount < info.output) {
                this.flash();
                att(new ApplyPowerAction(info.owner, this.owner, new FrostbitePower(info.owner, info.output-damageAmount), info.output-damageAmount));
            }
        }
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        atb(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0] + DESCRIPTIONS[3];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + DESCRIPTIONS[3];
    }
}
