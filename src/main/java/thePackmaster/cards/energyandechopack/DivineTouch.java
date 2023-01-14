package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.cards.blue.DoubleEnergy;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.energyandechopack.DivineTouchPower;
import thePackmaster.powers.energyandechopack.ReceptorPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class DivineTouch extends AbstractPackmasterCard {

    public final static String ID = makeID(DivineTouch.class.getSimpleName());

    private static final int COST = 2;

    public DivineTouch(){
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new DoubleEnergy();
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DivineTouchPower(p, 1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(1);
    }
}
