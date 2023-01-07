package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.distortionpack.DeteriorationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Deterioration extends AbstractPackmasterCard {
    public final static String ID = makeID("Deterioration");
    // intellij stuff power, self, uncommon, , , , , 5, 2

    public Deterioration() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DeteriorationPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}