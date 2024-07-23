package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.arcanapack.TheChariotPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class TheChariot extends AbstractAstrologerCard {
    public final static String ID = makeID("TheChariot");

    public TheChariot() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new TheChariotPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}