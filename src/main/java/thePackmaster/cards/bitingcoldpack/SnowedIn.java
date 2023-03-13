package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.powers.bitingcoldpack.SnowedInPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class SnowedIn extends BitingColdCard {
    public final static String ID = makeID("SnowedIn");

    public SnowedIn() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 13;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new FrostbitePower(m, magicNumber));
        applyToEnemy(m, new SnowedInPower(m, 1));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}