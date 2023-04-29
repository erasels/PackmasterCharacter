package thePackmaster.cardmodifiers.anomalypack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.RepeatCardAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;
import thePackmaster.cardmodifiers.madsciencepack.AbstractMadScienceModifier;
import thePackmaster.util.Wiz;

@AbstractCardModifier.SaveIgnore
//SaveIgnore necessary due to the presence of AbstractCard. will crash on load without it.
public class SigilModifier extends AbstractCardModifier {

    AbstractCard toPlayCard = null;

    public SigilModifier() {
        super();
    }

    public SigilModifier(AbstractCard absorbed) {
        super();
        toPlayCard = absorbed.makeSameInstanceOf();
    }

    public void onInitialApplication(AbstractCard card) {
        MultiCardPreview.add(card, toPlayCard);
    }

    @Override
    public void onExhausted(AbstractCard card) {
        Wiz.atb(new MakeTempCardInHandAction(toPlayCard.makeStatEquivalentCopy()));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                CardModifierManager.removeSpecificModifier(card, SigilModifier.this, true);
            }
        });
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractCard cpy = toPlayCard.makeSameInstanceOf();
        cpy.purgeOnUse = true;
        Wiz.atb(new NewQueueCardAction(cpy, true, false, true));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SigilModifier(toPlayCard);
    }
}