package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.bitingcoldpack.ColdSeasonPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ColdSeason extends AbstractPackmasterCard {
    public final static String ID = makeID("ColdSeason");

    public ColdSeason() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ColdSeasonPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}