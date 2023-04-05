package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Demolish extends AbstractBrickCard {
    public final static String ID = makeID(Demolish.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;

    private static final int DAMAGE = 12;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public Demolish() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseDamage = DAMAGE;
        cardsToPreview = new LooseBrick();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (damage < 12)
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        else
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
        shuffleIn(new LooseBrick());
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
