package thePackmaster.cards.bardinspirepack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bardinspirepack.InspirationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Heroism extends AbstractBardCard
{
    public final static String ID = makeID("Heroism");
    private static final int COST = 1;
    private static final int TEMP_HP = 5;
    private static final int UPGRADE_TEMP_HP = 3;
    private static final int INSPIRATION = 50;

    public Heroism()
    {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = TEMP_HP;
        secondMagic = baseSecondMagic = INSPIRATION;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new AddTemporaryHPAction(p, p, magicNumber));
        atb(new ApplyPowerAction(p, p, new InspirationPower(p, 1, secondMagic), 1));
    }

    @Override
    public void upp()
    {
        upgradeMagicNumber(UPGRADE_TEMP_HP);
    }
}
