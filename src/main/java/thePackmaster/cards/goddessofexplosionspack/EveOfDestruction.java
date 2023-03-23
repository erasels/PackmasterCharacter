package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.goddessofexplosionspack.EveOfDestructionPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EveOfDestruction extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("EveOfDestruction");

    public EveOfDestruction() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new EveOfDestructionPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}