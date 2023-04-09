package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Blueprints extends AbstractBrickCard {
    public final static String ID = makeID(Blueprints.class.getSimpleName());
    private static final int COST = 0;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 1;

    public Blueprints() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ScryAction(magicNumber));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
