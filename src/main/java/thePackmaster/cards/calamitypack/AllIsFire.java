package thePackmaster.cards.calamitypack;

import com.evacipated.cardcrawl.mod.stslib.variables.ExhaustiveVariable;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.bitingcoldpack.FrostbitePower;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;

public class AllIsFire extends AbstractCalamityCard {
    public static final String ID = SpireAnniversary5Mod.makeID("AllIsFire");
    private static final int COST = 1;
    private static final int EXHAUSTIVE = 2;
    private static final int UPGRADE_EXHAUSTIVE = 1;

    public AllIsFire() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        ExhaustiveVariable.setBaseValue(this, EXHAUSTIVE);
    }

    @Override
    public void upp() {
        ExhaustiveVariable.upgrade(this, UPGRADE_EXHAUSTIVE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                int igniteAmount = Wiz.pwrAmt(m, IgnitePower.POWER_ID) + 2 * (Wiz.pwrAmt(m, FrostbitePower.POWER_ID) + Wiz.pwrAmt(m, PoisonPower.POWER_ID));
                if (igniteAmount > 0) {
                    this.addToTop(new ApplyPowerAction(m, p, new IgnitePower(m, igniteAmount)));
                }
                this.addToTop(new RemoveSpecificPowerAction(m, p, PoisonPower.POWER_ID));
                this.addToTop(new RemoveSpecificPowerAction(m, p, FrostbitePower.POWER_ID));
                this.isDone = true;
            }
        });
    }
}
