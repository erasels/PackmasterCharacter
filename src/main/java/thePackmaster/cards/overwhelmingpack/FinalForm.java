package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FinalForm extends AbstractOverwhelmingCard {
    public final static String ID = makeID("ActuallyFinalForm");

    public FinalForm() {
        super(ID, 5, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);

        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 2; ++i) {
            AbstractCard form = SpireAnniversary5Mod.formCards.get(AbstractDungeon.cardRandomRng.random(SpireAnniversary5Mod.formCards.size() - 1)).makeCopy();
            form.modifyCostForCombat(-magicNumber);
            addToBot(new MakeTempCardInHandAction(form, 1));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}