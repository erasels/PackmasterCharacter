package thePackmaster.cards.warriorpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.warriorpack.SharpeningPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Sharpening extends AbstractPackmasterCard {

    public final static String ID = makeID(Sharpening.class.getSimpleName());

    private static final int COST = 1;

    public Sharpening(){
        super(ID, COST, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SharpeningPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upMagic(2);
    }
}
