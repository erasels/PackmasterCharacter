package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class Refrigerate extends BitingColdCard {
    public final static String ID = makeID("Refrigerate");

    public Refrigerate() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new FrostbitePower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}