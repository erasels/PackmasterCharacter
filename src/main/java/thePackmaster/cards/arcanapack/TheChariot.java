package thePackmaster.cards.arcanapack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.arcanapack.TheChariotPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class TheChariot extends AbstractAstrologerCard {
    public final static String ID = makeID("TheChariot");
    // intellij stuff power, self, rare, , , , , 4, 2

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