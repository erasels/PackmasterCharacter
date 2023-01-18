package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.contentcreatorpack.FrostprimePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Frostprime extends AbstractContentCard {
    public final static String ID = makeID("Frostprime");

    public Frostprime() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FrostprimePower(1, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}