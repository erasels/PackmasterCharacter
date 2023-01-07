package thePackmaster.cards.summonspack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.actions.summonspack.PewcumberAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.summonspack.ElephantDropEffect;
import thePackmaster.vfx.summonspack.LongElephantDropEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class SummonElephant extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonElephant.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int DAMAGE = 15;
    private static final int UPGRADE_DAMAGE = 4;
    private static final int MAGIC = 3;

    private static boolean seenThisSession;

    public SummonElephant() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!seenThisSession) {
            seenThisSession = true;
            AbstractGameEffect effect = new LongElephantDropEffect();
            vfx(effect, 2.35f);
        }  else if (MathUtils.random(0, 1f) < 0.05f)
            vfx(new LongElephantDropEffect(), 2.35f);
        else
            vfx(new ElephantDropEffect(), 0.45f);

        allDmg(AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        DamageInfo info = new DamageInfo(adp(), magicNumber, DamageInfo.DamageType.THORNS);
        atb(new DamageAction(adp(), info, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }

    static {
        seenThisSession = false;
    }
}
