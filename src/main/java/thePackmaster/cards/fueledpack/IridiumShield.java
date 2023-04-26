package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class IridiumShield extends AbstractFueledCard {
    public final static String ID = makeID(IridiumShield.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int BLOCK = 6;
    private static final int UPGRADE_BLOCK = 1;
    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public IridiumShield() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ExhaustAction(magicNumber, false, true, true));
    }

    @Override
    public void upp() {
        upgradeBlock(UPGRADE_BLOCK);
        upMagic(UPGRADE_MAGIC);
    }
}
