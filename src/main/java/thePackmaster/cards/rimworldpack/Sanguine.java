package thePackmaster.cards.rimworldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.rimworldpack.MoodPower;
import thePackmaster.powers.rimworldpack.SanguinePower;
import thePackmaster.powers.rimworldpack.TradeBeaconPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sanguine extends AbstractPackmasterCard {
    public final static String ID = makeID(Sanguine.class.getSimpleName());

    public Sanguine() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SanguinePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}