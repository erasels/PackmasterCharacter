package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.fueledpack.ControlledBurnPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ControlledBurn extends AbstractFueledCard {
    public final static String ID = makeID(ControlledBurn.class.getSimpleName());
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    public ControlledBurn() {
        super(ID, COST, TYPE, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ControlledBurnPower(1));
    }

    @Override
    public void upp() {
        isInnate = true;
    }
}
