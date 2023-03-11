package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.jockeypack.DerbyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.shuffleIn;

public class Derby extends AbstractJockeyCard {
    public final static String ID = makeID("Derby");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public Derby() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Horse();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        shuffleIn(new Horse(), magicNumber);
        applyToSelf(new DerbyPower());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}