package thePackmaster.powers.transmutationpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

public class CrystalResonancePower extends AbstractPackmasterPower implements TransmutableAffectingPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("CrystalResonancePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrystalResonancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        this.description = amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    @Override
    public void affectTransmutedCard(AbstractCard newCard) {
        addToBot(new DrawCardAction(amount));
    }
}
