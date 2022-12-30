package thePackmaster.cards.madsciencepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.legacypack.Cannonball;
import thePackmaster.powers.madsciencepack.PiratifyPower;
import thePackmaster.powers.madsciencepack.PiratifyPowerUpp;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Piratify extends AbstractPackmasterCard {
    public final static String ID = makeID("Piratify");

    public Piratify() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new Cannonball();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            Wiz.applyToSelf(new PiratifyPowerUpp(p, magicNumber));
        } else {
            Wiz.applyToSelf(new PiratifyPower(p, magicNumber));
        }
    }

    public void upp() {
       cardsToPreview.upgrade();
    }
}