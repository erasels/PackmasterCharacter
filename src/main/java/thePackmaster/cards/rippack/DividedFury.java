package thePackmaster.cards.rippack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.rippack.DividedFuryPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;


public class DividedFury extends AbstractRipCard {
    public final static String ID = makeID("DividedFury");

    public DividedFury() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upp() {
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DividedFuryPower(magicNumber));
    }
}
