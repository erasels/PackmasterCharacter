package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Burninate extends AbstractFueledCard {
    public final static String ID = makeID(Burninate.class.getSimpleName());
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    private static final int DAMAGE = 14;
    private static final int UPGRADE_DAMAGE = 5;

    public Burninate() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
        cardsToPreview = new HotAsh();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        DamageInfo info = new DamageInfo(adp(), damage, damageTypeForTurn);
        DamageAction action = new DamageAction(m, info, AbstractGameAction.AttackEffect.FIRE);
        atb(new ConsumeToDoAction(action));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
