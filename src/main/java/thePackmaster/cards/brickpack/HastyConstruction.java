package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class HastyConstruction extends AbstractBrickCard {
    public final static String ID = makeID(HastyConstruction.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 12;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public HastyConstruction() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
        cardsToPreview = new LooseBrick();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        forAllMonstersLiving(mo -> applyToEnemy(mo, new WeakPower(mo, magicNumber, false)));
        shuffleIn(new LooseBrick());
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
