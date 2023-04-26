package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BufferPower;
import thePackmaster.actions.fueledpack.ConsumeToDoAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class SmokeCover extends AbstractFueledCard {
    public final static String ID = makeID(SmokeCover.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int MAGIC = 1;

    public SmokeCover() {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ConsumeToDoAction(new ApplyPowerAction(adp(), adp(), new BufferPower(adp(), magicNumber))));
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADED_COST);
    }
}
