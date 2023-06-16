package thePackmaster.cards.lockonpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LockOnPower;
import thePackmaster.actions.highenergypack.AllEnemyApplyPowerAction;
import thePackmaster.powers.lockonpack.TunnelVisionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TunnelVision extends AbstractLockonCard {

    public final static String ID = makeID(TunnelVision.class.getSimpleName());

    public TunnelVision() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 0;
        baseSecondMagic = secondMagic = 1;
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (magicNumber > 0)
        {
            addToBot(new AllEnemyApplyPowerAction(p, magicNumber, (mon) -> new LockOnPower(mon, magicNumber)));
        }
        addToBot(new ApplyPowerAction(p, p, new TunnelVisionPower(p, secondMagic)));
    }
}
