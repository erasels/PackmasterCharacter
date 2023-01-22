package thePackmaster.cards.bardinspirepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.bardinspirepack.MagnumOpusAction;
import thePackmaster.powers.bardinspirepack.InspirationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class MagnumOpus extends AbstractBardCard
{
    public final static String ID = makeID("MagnumOpus");
    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;
    private static final int INSPIRATION = 25;
    private static final int INCREASE_INSPIRATION = 25;

    public MagnumOpus()
    {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        misc = secondMagic = baseSecondMagic = INSPIRATION;
        magicNumber = baseMagicNumber = INCREASE_INSPIRATION;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new ApplyPowerAction(p, p, new InspirationPower(p, 1, secondMagic), 1));
        atb(new MagnumOpusAction(uuid, misc, magicNumber));
    }

    @Override
    public void applyPowers()
    {
        secondMagic = baseSecondMagic = misc;
        super.applyPowers();
        initializeDescription();
    }

    @Override
    public void upp()
    {
        upgradeBaseCost(UPGRADE_COST);
    }

    @Override
    public AbstractCard makeStatEquivalentCopy() {
        MagnumOpus c = (MagnumOpus)super.makeStatEquivalentCopy();
        c.misc = c.secondMagic = c.baseSecondMagic = this.misc;
        return c;
    }
}
