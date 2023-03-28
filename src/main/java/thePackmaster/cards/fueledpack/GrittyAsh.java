package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GrittyAsh extends AbstractFueledCard {
    public final static String ID = makeID(GrittyAsh.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    private static final int BLOCK = 3;
    private static final int UPGRADE_BLOCK = 3;

    public GrittyAsh() {
        super(ID, COST, TYPE, RARITY, TARGET, CardColor.COLORLESS);
        baseBlock = BLOCK;
        tags.add(SpireAnniversary5Mod.ASH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    @Override
    public void upgrade() {
        upgradeBlock(UPGRADE_BLOCK);
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
