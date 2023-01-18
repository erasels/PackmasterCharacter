package thePackmaster.cards.replicatorspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.replicatorspack.IterativeDesignPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class IterativeDesign extends AbstractReplicatorCard {


    public final static String ID = makeID("IterativeDesign");

    public IterativeDesign() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p,p,new IterativeDesignPower(p, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}
