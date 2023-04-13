package thePackmaster.powers.batterpack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OpenBracketPower extends AbstractPackmasterPower{
    public AbstractCreature source;

    public static final String POWER_ID = makeID("OpenBracketPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public OpenBracketPower(final AbstractCreature owner, final int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, -1);
    }

    @Override
    public boolean canPlayCard(AbstractCard card) {
        if(card.type != AbstractCard.CardType.ATTACK)
            return false;
        return true;
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer)
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
