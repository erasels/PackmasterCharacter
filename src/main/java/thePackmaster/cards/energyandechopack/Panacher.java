package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.energyandechopack.PanacherPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Panacher extends AbstractEchoCard {

    public final static String ID = makeID(Panacher.class.getSimpleName());

    private static final int COST = 0;

    public Panacher(){
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 10;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PanacherPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upMagic(4);
    }
}
