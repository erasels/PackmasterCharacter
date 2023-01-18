package thePackmaster.cards.replicatorspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.replicatorspack.EnergyDrinkPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class EnergyDrink extends AbstractReplicatorCard {


    public final static String ID = makeID("EnergyDrink");

    public EnergyDrink() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AddTemporaryHPAction(p,p,magicNumber));
        atb(new ApplyPowerAction(p,p,new EnergyDrinkPower(p, 1)));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}