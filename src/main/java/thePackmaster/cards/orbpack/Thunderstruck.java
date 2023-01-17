package thePackmaster.cards.orbpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.orbpack.ThunderstruckPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Thunderstruck extends AbstractOrbCard {
    public final static String ID = makeID("Thunderstruck");
    // intellij stuff power, none, rare, , , , , 1, 

    public Thunderstruck() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ThunderstruckPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}