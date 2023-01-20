package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class StormForm extends AbstractHighEnergyCard {
    public final static String ID = makeID("StormForm");
    // intellij stuff power, self, rare, , , , , 2, 1

    public StormForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("ORB_LIGHTNING_EVOKE", 0.1F));
        applyToSelf(new BerserkPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}