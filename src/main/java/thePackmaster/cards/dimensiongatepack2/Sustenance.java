package thePackmaster.cards.dimensiongatepack2;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardVault;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.forAllMonstersLiving;

public class Sustenance extends AbstractDimensionalCardVault {
    public final static String ID = makeID("Sustenance");

    public Sustenance() {
        super(ID, 1, CardRarity.COMMON, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseBlock = 5;
        baseMagicNumber = magicNumber = 3;
        PersistFields.setBaseValue(this, 2);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        AbstractMonster m2 = Wiz.getRandomEnemy();
        Wiz.applyToEnemy(m2, new PoisonPower(m2, p, magicNumber));
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}