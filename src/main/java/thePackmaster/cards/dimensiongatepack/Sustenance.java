package thePackmaster.cards.dimensiongatepack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.PersistFields;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardVault;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sustenance extends AbstractDimensionalCardVault {
    public final static String ID = makeID("Sustenance");

    public Sustenance() {
        super(ID, 1, CardRarity.COMMON, CardType.SKILL, CardTarget.ALL_ENEMY);
        baseBlock = 5;
        baseMagicNumber = magicNumber = 2;
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