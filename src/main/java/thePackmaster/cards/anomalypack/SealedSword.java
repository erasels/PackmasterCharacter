package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;

public class SealedSword extends AbstractAnomalyCard {
    public static final String ID = SpireAnniversary5Mod.makeID("SealedSword");
    private static final int COST = 6;

    public SealedSword() {
        super(ID, COST, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        this.exhaust = true;
        this.cardsToPreview = new SouleaterStrike();
    }

    @Override
    public void upp() {
        this.cardsToPreview.upgrade();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {    }

    @Override
    public void triggerOnExhaust() {
        this.addToBot(new MakeTempCardInDrawPileAction(this.cardsToPreview, 1, true, true));
    }
}
