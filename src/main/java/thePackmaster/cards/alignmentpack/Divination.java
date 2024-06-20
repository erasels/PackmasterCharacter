package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Divination extends AbstractAstrologerCard {
    public final static String ID = makeID("Divination");
    // intellij stuff skill, none, common, , , , , 3, 

    public Divination() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded)
        {
            atb(new HandSelectAction(999999, (c)->true, (cards)->{
                for (AbstractCard c : cards)
                    p.hand.moveToBottomOfDeck(c);
            }, null, cardStrings.EXTENDED_DESCRIPTION[0], false, true, true));
        }
        else
        {
            atb(new HandSelectAction(1, (c)->true, (cards)->{
                for (AbstractCard c : cards)
                    p.hand.moveToBottomOfDeck(c);
            }, null, cardStrings.EXTENDED_DESCRIPTION[0], false, false, false));
        }
        atb(new DrawCardAction(p, this.magicNumber));
    }

    public void upp() {
    }
}