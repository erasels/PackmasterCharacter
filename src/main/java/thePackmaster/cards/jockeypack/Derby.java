package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.shuffleIn;

public class Derby extends AbstractPackmasterCard {
    public final static String ID = makeID("Derby");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public Derby() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Horse();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        shuffleIn(new Horse(), magicNumber);
        //TODO: Derby Power
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}