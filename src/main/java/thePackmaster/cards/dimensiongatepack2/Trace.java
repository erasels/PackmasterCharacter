package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardObelisk;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardVault;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.forAllMonstersLiving;

public class Trace extends AbstractDimensionalCardObelisk {
    public final static String ID = makeID("Trace");

    public Trace() {
        super(ID, 0, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ScryAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}