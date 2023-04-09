package thePackmaster.powers.summonerspellspack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GhostedPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GhostedPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GhostedPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, 1);
        this.amount = amount;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target.currentHealth <= damageAmount) {
            addToBot(new ApplyPowerAction(Wiz.p(), Wiz.p(), new GhostedPower(Wiz.p(), 1), 1));
        }
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new DrawCardAction(this.amount));

        if (this.amount > 1)
            this.addToBot(new ReducePowerAction(this.owner, this.owner, GhostedPower.POWER_ID, 1));
        else
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, GhostedPower.POWER_ID));
    }
    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = this.amount == 1 ?
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] :
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }

}
