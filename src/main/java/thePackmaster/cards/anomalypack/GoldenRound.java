package thePackmaster.cards.anomalypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.anomalypack.GoldenRoundAction;
import thePackmaster.cards.AbstractPackmasterCard;

public class GoldenRound extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("GoldenRound");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;

    public GoldenRound() {
        super(ID, COST, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
    }

    @Override
    public void upp() {

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GoldenRoundAction(this));
    }


    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }
}
