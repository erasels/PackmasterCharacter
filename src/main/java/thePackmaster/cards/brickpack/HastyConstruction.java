package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class HastyConstruction extends AbstractBrickCard {
    public final static String ID = makeID(HastyConstruction.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 8;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public HastyConstruction() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new DrawCardAction(magicNumber));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
