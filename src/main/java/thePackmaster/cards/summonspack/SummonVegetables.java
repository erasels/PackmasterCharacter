package thePackmaster.cards.summonspack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.summonspack.PewcumberAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class SummonVegetables extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonVegetables.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE = 2;

    public SummonVegetables() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int n = getAttackCount();
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0 && n >= 5 )
                atb(new PewcumberAction(m, new DamageInfo(adp(), damage, DamageInfo.DamageType.NORMAL), true));
            else
                atb(new PewcumberAction(m, new DamageInfo(adp(), damage, DamageInfo.DamageType.NORMAL), false));
        }
    }

    public int getAttackCount() {
        return (int) AbstractDungeon.actionManager.cardsPlayedThisTurn.stream().filter(c -> c.type == CardType.ATTACK).count();
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
