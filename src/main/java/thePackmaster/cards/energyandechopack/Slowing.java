package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.cards.status.Wound;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BerserkPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.energyandechopack.ReceptorPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.makeInHand;

public class Slowing extends AbstractPackmasterCard {

    public final static String ID = makeID(Slowing.class.getSimpleName());

    private static final int COST = 2;

    public Slowing(){
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
        cardsToPreview = new Wound();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new BerserkPower(p, 1));
        makeInHand(cardsToPreview.makeCopy(), magicNumber);
    }

    @Override
    public void upp() {
        upMagic(-2);
    }
}
