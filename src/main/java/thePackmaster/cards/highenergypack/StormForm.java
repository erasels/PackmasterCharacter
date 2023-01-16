package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class StormForm extends AbstractHighEnergyCard {
    public final static String ID = makeID("StormForm");
    // intellij stuff power, self, rare, , , , , 2, 1

    public StormForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BerserkPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}