package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class SpartanShield extends IntoTheBreachCard {
    public final static String ID = makeID("SpartanShield");

    public SpartanShield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        block = baseBlock = 7;
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new LoseStrengthPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}
