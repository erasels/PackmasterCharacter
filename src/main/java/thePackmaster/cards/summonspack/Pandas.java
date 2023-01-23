package thePackmaster.cards.summonspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.summonspack.PandaXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.summonspack.SummonPandasPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class Pandas extends AbstractSummonsCard {
    public final static String ID = makeID(Pandas.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = -1;
    private static final int UPGRADED_COST = 0;

    public Pandas() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new PandaXCostAction(freeToPlayOnce, energyOnUse, upgraded));
    }

    @Override
    public void upp() {
    }
}
