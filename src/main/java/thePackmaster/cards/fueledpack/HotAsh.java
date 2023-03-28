package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class HotAsh extends AbstractFueledCard {
    public final static String ID = makeID(HotAsh.class.getSimpleName());
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 4;

    public HotAsh() {
        super(ID, COST, TYPE, RARITY, TARGET, CardColor.COLORLESS);
        baseDamage = DAMAGE;
        tags.add(SpireAnniversary5Mod.ASH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    @Override
    public void upgrade() {
        upgradeDamage(UPGRADE_DAMAGE);
        upgraded = true;
        timesUpgraded++;
        name = cardStrings.NAME + "+" + timesUpgraded;
        initializeTitle();
    }

    @Override
    public void upp() {
        upgrade();
    }
}
