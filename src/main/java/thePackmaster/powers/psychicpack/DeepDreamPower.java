package thePackmaster.powers.psychicpack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.actions.psychicpack.MakeCardInHandOccultAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class DeepDreamPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DeepDreamPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public DeepDreamPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        this.flash();
        for (int i = 0; i < amount; ++i)
            addToBot(new MakeCardInHandOccultAction(true));
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1)
        {
            this.description = DESCRIPTIONS[0];
        }
        else
        {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
