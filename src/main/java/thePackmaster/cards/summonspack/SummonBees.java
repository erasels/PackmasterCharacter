package thePackmaster.cards.summonspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.summonspack.SummonBeesAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.summonspack.TreeBlockadePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.atb;

public class SummonBees extends AbstractPackmasterCard {
    public final static String ID = makeID(SummonBees.class.getSimpleName());
    private static final int COST = -1;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;


    public SummonBees() {
        super(ID, COST, TYPE, RARITY, TARGET);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SummonBeesAction(freeToPlayOnce, energyOnUse, upgraded));
    }

    @Override
    public void upp() {
        uDesc();
    }
}
