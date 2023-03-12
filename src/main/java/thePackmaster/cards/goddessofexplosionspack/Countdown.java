package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.goddessofexplosionspack.CountdownPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Countdown extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("Countdown");

    private static final int MAGIC = 5;
    private static final int MAGIC_UP = -1;

    public Countdown() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        cardsToPreview = new AtomicPiledriver();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new CountdownPower(p,magicNumber,upgraded));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(MAGIC_UP);
        cardsToPreview.upgrade();
    }
}
