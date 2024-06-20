package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.energyandechopack.SlowingPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.makeInHand;

public class Slowing extends AbstractEchoCard {

    public final static String ID = makeID(Slowing.class.getSimpleName());

    private static final int COST = 2;

    public Slowing(){
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
        cardsToPreview = new Wound();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SlowingPower(p, 1));
        makeInHand(cardsToPreview.makeCopy(), magicNumber);
    }

    @Override
    public void upp() {
        upMagic(-1);
    }
}
