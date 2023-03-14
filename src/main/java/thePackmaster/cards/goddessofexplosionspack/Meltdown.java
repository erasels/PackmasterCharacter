package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.goddessofexplosionspack.MeltdownPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Meltdown extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("Meltdown");

    public Meltdown() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new ApplyPowerAction(p, p, new MeltdownPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}
