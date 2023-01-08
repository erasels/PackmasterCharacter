package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.wardenpack.EnigmancyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Enigmancy extends AbstractPackmasterCard {
    public final static String ID = makeID("Enigmancy");

    private static final int BASE_SCRY = 5;
    private static final int SCRY_UP = 1;
    private static final int BASE_EN = 2;
    private static final int EN_UP = 1;

    public Enigmancy() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = BASE_SCRY;
        secondMagic = baseSecondMagic = BASE_EN;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ScryAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new EnigmancyPower(p, this.secondMagic), this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(SCRY_UP);
        upgradeSecondMagic(EN_UP);
    }
}
