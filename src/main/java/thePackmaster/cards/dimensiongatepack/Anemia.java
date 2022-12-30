package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Anemia extends AbstractDimensionalCard {
    public final static String ID = makeID("Anemia");

    public Anemia() {
        super(ID, 1, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseBlock = 8;
        baseMagicNumber = 1;
        setFrame("anemiaframe.png");

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        forAllMonstersLiving(this::applyEffect);
    }

    public void applyEffect(AbstractMonster q){
        if (q.hasPower(PoisonPower.POWER_ID)){
            applyToEnemy(q, new WeakPower(q, magicNumber, false));
        }
    }

    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);

    }
}