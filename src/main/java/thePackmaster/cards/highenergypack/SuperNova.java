package thePackmaster.cards.highenergypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.arcanapack.AllEnemyLoseHPAction;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SuperNova extends AbstractHighEnergyCard {
    public final static String ID = makeID("SuperNova");
    // intellij stuff skill, all_enemy, rare, , , , , 67, 17

    public SuperNova() {
        super(ID, 6, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 67;
        baseSecondMagic = secondMagic = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new AllEnemyLoseHPAction(p, magicNumber));
        atb(new AllEnemyApplyPowerAction(p, secondMagic, (q) -> new WeakPower(q, secondMagic, false)));
        atb(new AllEnemyApplyPowerAction(p, secondMagic, (q) -> new VulnerablePower(q, secondMagic, false)));
    }

    public void upp() {
        upgradeMagicNumber(11);
        upgradeSecondMagic(2);
    }
}