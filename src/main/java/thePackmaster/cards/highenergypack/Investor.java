package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.highenergypack.InvestorPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Investor extends AbstractHighEnergyCard {
    public final static String ID = makeID("Investor");
    // intellij stuff power, self, uncommon, , , , , 6, 3

    public Investor() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new InvestorPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}