package thePackmaster.cards.energyandechopack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.energyandechopack.ReceptorPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Receptor extends AbstractPackmasterCard {

    public final static String ID = makeID(Receptor.class.getSimpleName());

    private static final int COST = 1;

    public Receptor(){
        super(ID, COST, AbstractCard.CardType.POWER, AbstractCard.CardRarity.UNCOMMON, AbstractCard.CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ReceptorPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upMagic(1);
    }
}
