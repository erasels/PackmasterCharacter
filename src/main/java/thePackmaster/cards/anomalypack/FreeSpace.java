package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class FreeSpace extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("FreeSpace");
    private static final int COST = 1;

    public FreeSpace() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.isEthereal = true;
    }

    @Override
    public void upp() {
        this.isEthereal = false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(1));
    }

    @Override
    public void triggerOnExhaust() {
        Wiz.atb(new DrawCardAction(1));
    }

    @Override
    public void triggerOnManualDiscard() {
        Wiz.atb(new DrawCardAction(1));
    }
}
