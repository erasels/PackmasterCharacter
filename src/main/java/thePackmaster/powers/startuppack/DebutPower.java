package thePackmaster.powers.startuppack;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static org.apache.commons.lang3.math.NumberUtils.min;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DebutPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DebutPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private boolean triggered = false;

    public DebutPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void atStartOfTurn(){
        this.triggered = false;
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if(card.isInnate && card.costForTurn > 0 && !triggered) {
            addToBot(new GainEnergyAction(min(this.amount, card.costForTurn)));
            this.triggered = true;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
