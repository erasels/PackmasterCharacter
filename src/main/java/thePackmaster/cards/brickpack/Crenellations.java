package thePackmaster.cards.brickpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Crenellations extends AbstractBrickCard implements StartupCard {
    public final static String ID = makeID(Crenellations.class.getSimpleName());
    private static final int COST = -2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;

    private static final int DAMAGE = 9;
    private static final int UPGRADE_DAMAGE = 3;

    public Crenellations() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        calculateCardDamage(null);
        allDmg(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        return true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
