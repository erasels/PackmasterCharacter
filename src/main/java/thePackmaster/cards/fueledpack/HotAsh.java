package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class HotAsh extends AbstractFueledCard {
    public final static String ID = makeID(HotAsh.class.getSimpleName());
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_DAMAGE = 3;
    private static final int MAGIC = 2;

    public HotAsh() {
        super(ID, COST, TYPE, RARITY, TARGET, CardColor.COLORLESS);
        baseDamage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        atb(new LoseHPAction(adp(), adp(), magicNumber, AbstractGameAction.AttackEffect.FIRE));
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
