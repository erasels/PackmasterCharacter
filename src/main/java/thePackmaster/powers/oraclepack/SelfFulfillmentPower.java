package thePackmaster.powers.oraclepack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.cards.oraclepack.Prophecy;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SelfFulfillmentPower extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("SelfFulfillmentPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public SelfFulfillmentPower(AbstractCreature owner) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, -1);
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof Prophecy) {
            this.flash();
            action.exhaustCard = false;
        }
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
