package thePackmaster.cards.summonspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SummonBulldog extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonBulldog.class.getSimpleName());
    private static final int COST = 0;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_DAMAGE = 2;

    public SummonBulldog() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage);
        Wiz.discard(1);
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
