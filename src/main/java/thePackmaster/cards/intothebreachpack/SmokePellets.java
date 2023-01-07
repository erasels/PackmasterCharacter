package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.getEnemies;

public class SmokePellets extends IntoTheBreachCard {
    public final static String ID = makeID("SmokePellets");

    public SmokePellets() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = 6;
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (AbstractMonster mo : getEnemies())
            applyToEnemy(mo, new WeakPower(mo, magicNumber, false));
    }

    public void upp() {
        upgradeBlock(3);
    }
}