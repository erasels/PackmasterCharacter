package thePackmaster.powers.dimensiongatepack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ArsenalGearPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ArsenalGearPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public ArsenalGearPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);

    }

    @Override
    public void atStartOfTurnPostDraw() {
        ArrayList<AbstractCard> validCards;
        AbstractCard c;
        //TODO - Test this when the cardpool is properly initialized.  This is evidence there is no pool yet.
        validCards = Wiz.getCardsMatchingPredicate(c2 -> c2.rarity == AbstractCard.CardRarity.UNCOMMON || c2.rarity == AbstractCard.CardRarity.RARE);
        if (validCards.size() > 0) {
            c = validCards.get(0).makeCopy();
            c.modifyCostForCombat(-9);
            addToBot(new MakeTempCardInHandAction(c));
            validCards.remove(0);
        }
        addToBot(new ReducePowerAction(owner, owner, this, 1));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
