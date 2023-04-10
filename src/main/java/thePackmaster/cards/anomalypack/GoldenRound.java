package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.anomalypack.GoldenRoundAction;

public class GoldenRound extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GoldenRound");
    private static final int COST = 1;

    public GoldenRound() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
    }

    @Override
    public void upp() {
        upgradeBaseCost(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GoldenRoundAction(this));
    }
}
