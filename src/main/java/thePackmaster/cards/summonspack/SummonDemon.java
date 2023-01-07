package thePackmaster.cards.summonspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.summonspack.SummonDemonAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.summonspack.SummonPandasPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class SummonDemon extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonDemon.class.getSimpleName());
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 2;

    public SummonDemon() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SummonDemonAction(upgraded));
    }

    @Override
    public void upp() {
        uDesc();
    }
}
