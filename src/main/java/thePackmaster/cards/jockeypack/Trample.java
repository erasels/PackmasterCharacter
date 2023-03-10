package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ChokePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class Trample extends AbstractPackmasterCard {
    public final static String ID = makeID("Trample");
    // intellij stuff skill, enemy, common, , , , , 2, 

    public Trample() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new WeakPower(m, 2, false));
        applyToEnemy(m, new ChokePower(m, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}