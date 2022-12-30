package thePackmaster.powers.downfallpack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class VexVinciblePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("VexVinciblePower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean activated = false;

    public VexVinciblePower(AbstractCreature owner, int amount, int amount2) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);
        this.activated = false;
        this.isTwoAmount = true;
        this.amount2 = amount2;
        this.updateDescription();
    }

    public int onLoseHp(int damageAmount) {
        if (damageAmount > this.amount2) {
            damageAmount = this.amount2;
            activated = true;
        }

        this.amount2 -= damageAmount;
        if (this.amount2 < 0) {
            this.amount2 = 0;
        }

        this.updateDescription();

        return damageAmount;
    }

    @Override
    public void atEndOfRound(){
        if (activated) {
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
        this.amount2 = 5;
    }

    public void updateDescription() {
        if (this.amount2 == 1) {
            this.description = DESCRIPTIONS[0] + this.amount2 + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[2] + this.amount2 + DESCRIPTIONS[3] + amount + DESCRIPTIONS[4];
        }
    }
}
