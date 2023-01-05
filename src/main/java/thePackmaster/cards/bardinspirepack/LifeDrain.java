package thePackmaster.cards.bardinspirepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.bardinspirepack.LifeDrainAction;
import thePackmaster.vfx.bardinspirepack.LifeDrainEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class LifeDrain extends AbstractBardCard
{
    public final static String ID = makeID("LifeDrain");
    private static final int COST = 2;
    private static final int DAMAGE = 8;
    private static final int UPGRADE_DAMAGE = 4;

    public LifeDrain()
    {
        super(ID, COST, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        atb(new VFXAction(new LifeDrainEffect(m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY), 0.5f));
        atb(new LifeDrainAction(m, p, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upp()
    {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
