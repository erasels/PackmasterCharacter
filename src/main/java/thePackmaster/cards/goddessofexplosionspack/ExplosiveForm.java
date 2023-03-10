package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.goddessofexplosionspack.ExplosiveFormPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ExplosiveForm extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("ExplosiveForm");

    public ExplosiveForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new ExplosiveFormPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        isEthereal = false;
    }
}
