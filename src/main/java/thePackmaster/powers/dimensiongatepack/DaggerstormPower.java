package thePackmaster.powers.dimensiongatepack;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;
import thePackmaster.util.creativitypack.onGenerateCardMidcombatInterface;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DaggerstormPower extends AbstractPackmasterPower implements onGenerateCardMidcombatInterface {
    public static final String POWER_ID = makeID("DaggerstormPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public DaggerstormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);

    }

    @Override
    public void onCreateCard(AbstractCard card) {
        if (!(card instanceof Shiv)){
            for (int i = 0; i < amount; i++) {
                Shiv shiv = new Shiv();
                shiv.purgeOnUse = true;
                Wiz.atb(new NewQueueCardAction(shiv, true));
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] ;
        } else {
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
