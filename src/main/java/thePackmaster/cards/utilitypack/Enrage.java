package thePackmaster.cards.utilitypack;

import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.stances.aggressionpack.AggressionStance;

public class Enrage extends AbstractPackmasterCard {
    public static final String ID = SpireAnniversary5Mod.makeID("Enrage");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    private static final int COST = 1;
    private static final int DRAW = 1;
    private static final int UPGRADE_DRAW = 1;
    private static final int DISCARD = 2;

    public Enrage() {
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.magicNumber = this.baseMagicNumber = DRAW;
        this.secondMagic = this.baseSecondMagic = DISCARD;
    }

    @Override
    public void upp() {
        this.upgradeMagicNumber(UPGRADE_DRAW);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster unused) {
        this.addToBot(new DiscardAction(p, p, this.secondMagic, false));
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new ChangeStanceAction(new AggressionStance()));
    }
}
