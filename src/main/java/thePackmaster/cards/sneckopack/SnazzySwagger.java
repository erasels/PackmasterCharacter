package thePackmaster.cards.sneckopack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.RandomizeHandCostAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SnazzySwagger extends AbstractPackmasterCard {


    public final static String ID = makeID("SnazzySwagger");

    public SnazzySwagger() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new RandomizeHandCostAction());
        addToBot(new DiscardAction(p, p, magicNumber, false));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
