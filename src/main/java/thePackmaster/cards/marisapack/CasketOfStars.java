package thePackmaster.cards.marisapack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import thePackmaster.powers.marisapack.StarCasketPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CasketOfStars extends AbstractMarisaCard implements AmplifyCard{
    public final static String ID = makeID(CasketOfStars.class.getSimpleName());
    private static final int MAGIC = 4, UPG_MAGIC = 2, MAGIC2 = 1;

    public CasketOfStars() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = MAGIC2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StarCasketPower(secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(UPG_MAGIC);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new MetallicizePower(Wiz.p(), magicNumber));
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }
}
