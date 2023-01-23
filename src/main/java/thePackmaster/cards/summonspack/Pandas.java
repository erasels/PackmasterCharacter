package thePackmaster.cards.summonspack;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.orbs.summonspack.Panda;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class Pandas extends AbstractSummonsCard {
    public final static String ID = makeID(Pandas.class.getSimpleName());
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int COST = -1;

    public Pandas() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (params[0] == 1)
                effect++;
            for (int i = 0; i < effect; i++)
                att(new ChannelAction(new Panda()));
            return true;
        }, upgraded ? 1 : 0));
    }

    @Override
    public void upp() {
    }
}
