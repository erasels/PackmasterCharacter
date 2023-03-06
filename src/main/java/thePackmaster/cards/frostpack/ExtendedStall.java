package thePackmaster.cards.frostpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.frostpack.ExtendedStallPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ExtendedStall extends AbstractFrostCard {
    public final static String ID = makeID("ExtendedStall");

    public ExtendedStall() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ExtendedStallPower(p, this.magicNumber));
    }

    public void upp() {
        this.isInnate = true;
    }
}