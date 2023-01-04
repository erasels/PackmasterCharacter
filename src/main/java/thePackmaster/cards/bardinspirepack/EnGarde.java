package thePackmaster.cards.bardinspirepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.bardinspirepack.InspirationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class EnGarde extends AbstractBardCard
{
    public final static String ID = makeID("EnGarde");
    private static final int COST = 2;
    private static final int DAMAGE = 7;
    private static final int BLOCK = 7;
    private static final int INSPIRATION_TIMES = 2;
    private static final int UPGRADE_INSPIRATION_TIMES = 1;

    public EnGarde()
    {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = INSPIRATION_TIMES;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new GainBlockAction(p, p, block));
        atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void applyPowers()
    {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.ID.equals(InspirationPower.POWER_ID) && power instanceof InspirationPower) {
                ((InspirationPower) power).amount2 *= magicNumber;
            }
        }

        super.applyPowers();

        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.ID.equals(InspirationPower.POWER_ID) && power instanceof InspirationPower) {
                ((InspirationPower) power).amount2 /= magicNumber;
            }
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.ID.equals(InspirationPower.POWER_ID) && power instanceof InspirationPower) {
                ((InspirationPower) power).amount2 *= magicNumber;
            }
        }

        super.calculateCardDamage(mo);

        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power.ID.equals(InspirationPower.POWER_ID) && power instanceof InspirationPower) {
                ((InspirationPower) power).amount2 /= magicNumber;
            }
        }
    }


    @Override
    public void upp()
    {
        upgradeMagicNumber(UPGRADE_INSPIRATION_TIMES);
    }
}
