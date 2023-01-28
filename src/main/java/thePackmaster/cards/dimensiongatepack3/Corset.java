package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardEden;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Corset extends AbstractDimensionalCardEden {
    public final static String ID = makeID("Corset");

    public Corset() {
        super(ID, 1, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseBlock = 16;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelfDamageAction(new DamageInfo(p, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        blck();
    }

    public void upp() {
        upgradeBlock(4);

    }
}