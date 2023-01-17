package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.curses.Doubt;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

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
