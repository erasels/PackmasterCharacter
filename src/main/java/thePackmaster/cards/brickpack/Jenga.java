package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.brickpack.JengaPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Jenga extends AbstractBrickCard {
    public final static String ID = makeID(Jenga.class.getSimpleName());
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int MAGIC = 1;

    public Jenga() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new JengaPower(1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}
