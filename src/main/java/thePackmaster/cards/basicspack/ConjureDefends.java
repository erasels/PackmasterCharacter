package thePackmaster.cards.basicspack;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.evacipated.cardcrawl.mod.stslib.actions.common.RefundAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
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
        this.exhaust = true;
        this.cardsToPreview = new Defend();
        this.cardsToPreview.upgrade();
        this.cardsToPreview.rawDescription += CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("DuplicateModifier")).TEXT[3];
        this.cardsToPreview.initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCard card = this.cardsToPreview.makeStatEquivalentCopy();
        if (this.upgraded) {
            addToBot(new ConjureCardsAction(p, this.freeToPlayOnce, this.energyOnUse + 1, card));
        } else addToBot(new ConjureCardsAction(p, this.freeToPlayOnce, this.energyOnUse, card));
        addToBot(new RefundAction(this, 1));
    }

    public void upp(){}
}
