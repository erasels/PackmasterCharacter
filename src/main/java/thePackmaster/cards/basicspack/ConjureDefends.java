package thePackmaster.cards.basicspack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.RefundAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.basicpack.ConjureCardsAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Defend;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ConjureDefends extends AbstractPackmasterCard {
    public final static String ID = makeID("ConjureDefends");

    public ConjureDefends() {
        super(ID, -1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
        this.isEthereal = true;
        this.exhaust = true;
        this.cardsToPreview = new Defend();
        this.cardsToPreview.rawDescription += CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT[2];
        this.cardsToPreview.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = this.cardsToPreview.makeStatEquivalentCopy();
        addToBot(new ConjureCardsAction(p, this.freeToPlayOnce, this.energyOnUse, card));
        addToBot(new RefundAction(this, 1));
    }

    public void upp(){
        this.isEthereal = false;
    }
}
