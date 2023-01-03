package thePackmaster.cards.bardinspirepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bardinspirepack.SplendidPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.p;

public class SplendidForm extends AbstractBardCard
{
    public final static String ID = makeID("SplendidForm");
    private static final int COST = 3;
    private static final int INSPIRATION = 100;
    private static final int UPGRADE_INSPIRATION = 100;

    public SplendidForm()
    {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        secondMagic = baseSecondMagic = INSPIRATION;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new ApplyPowerAction(p(), p(), new SplendidPower(p(), secondMagic, 1), 1));
    }

    @Override
    public void upp()
    {
        upgradeSecondMagic(UPGRADE_INSPIRATION);
    }
}
