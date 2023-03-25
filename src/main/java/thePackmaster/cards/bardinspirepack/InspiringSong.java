package thePackmaster.cards.bardinspirepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.powers.bardinspirepack.InspirationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class InspiringSong extends AbstractBardCard
{
    public final static String ID = makeID("InspiringSong");
    private static final int COST = 1;
    private static final int ORBS = 1;
    private static final int INSPIRATION = 50;
    private static final int UPGRADE_INSPIRATION = 25;

    public InspiringSong()
    {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = ORBS;
        secondMagic = baseSecondMagic = INSPIRATION;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = ORBS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        for (int i = 0; i < magicNumber; ++i) {
            atb(new ChannelAction(new Frost()));
        }
        atb(new AbstractGameAction()
        {
            @Override
            public void update()
            {
                int count = (int) p.orbs.stream()
                        .filter(orb -> !(orb instanceof EmptyOrbSlot))
                        .count();
                if (count > 0) {
                    atb(new ApplyPowerAction(p, p, new InspirationPower(p, count, secondMagic), count));
                }
                isDone = true;
            }
        });
    }

    @Override
    public void upp()
    {
        upgradeSecondMagic(UPGRADE_INSPIRATION);
    }
}
