package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.HandSelectAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Approach extends AbstractOverwhelmingCard {
    public final static String ID = makeID("Approach");

    public Approach() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);

        this.magicNumber = this.baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(this.magicNumber, approachCallback(1)));
    }

    private AbstractGameAction approachCallback(int reduction) {
        return new HandSelectAction(1, (c)->DrawCardAction.drawnCards.contains(c), cards -> {
            for (AbstractCard c : cards) {
                if (c.costForTurn > 0)
                    c.setCostForTurn(c.costForTurn - reduction);
            }
        }, null, cardStrings.EXTENDED_DESCRIPTION[0], true, false, false);
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}