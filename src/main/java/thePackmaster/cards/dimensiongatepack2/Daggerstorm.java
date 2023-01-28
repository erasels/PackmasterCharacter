package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardRoguebook;
import thePackmaster.powers.dimensiongatepack.DaggerstormPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;


public class Daggerstorm extends AbstractDimensionalCardRoguebook {
    public final static String ID = makeID("Daggerstorm");

    public Daggerstorm() {
        super(ID, 3, CardRarity.RARE, CardType.POWER, CardTarget.SELF);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
       applyToSelf(new DaggerstormPower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}