package thePackmaster.powers.bitingcoldpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FrigidBodyPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("FrigidBodyPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public FrigidBodyPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, 0);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            if (damageAmount == 0) {
                this.flash();
                this.addToTop(new ApplyPowerAction(info.owner, this.owner, new FrostbitePower(info.owner, info.output), info.output));
            } else if (damageAmount < info.output) {
                this.flash();
                this.addToTop(new ApplyPowerAction(info.owner, this.owner, new FrostbitePower(info.owner, info.output-damageAmount), info.output-damageAmount));
            }
        }
        return damageAmount;
    }

    @Override
    public void atEndOfRound() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
