package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class ThrowDebris extends AbstractBrickCard {
    public final static String ID = makeID(ThrowDebris.class.getSimpleName());
    private static final int COST = 2;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int DAMAGE = 4;
    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 1;

    public ThrowDebris() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (damage < 15) {
            for (int i = 0; i < magicNumber; i++)
                dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
        else {
            for (int i = 0; i < magicNumber; i++)
                dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
