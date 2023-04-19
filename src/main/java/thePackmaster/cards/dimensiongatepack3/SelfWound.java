package thePackmaster.cards.dimensiongatepack3;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTowerTactics;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SelfWound extends AbstractDimensionalCardTowerTactics {
    public final static String ID = makeID("SelfWound");

    public SelfWound() {
        super(ID, 0, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelfDamageAction(new DamageInfo(p, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        Wiz.atb(new AddTemporaryHPAction(p,p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}