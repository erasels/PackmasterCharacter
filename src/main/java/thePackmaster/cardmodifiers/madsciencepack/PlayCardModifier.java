package thePackmaster.cardmodifiers.madsciencepack;

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
import com.megacrit.cardcrawl.helpers.FontHelper;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.RepeatCardAction;
import thePackmaster.cardmodifiers.anomalypack.SigilModifier;
import thePackmaster.util.Wiz;

@AbstractCardModifier.SaveIgnore
//SaveIgnore necessary due to the presence of AbstractCard. will crash on load without it.
public class PlayCardModifier extends AbstractMadScienceModifier {
    AbstractCard toPlayCard = null;

    public PlayCardModifier(int valueIn) {
        super(valueIn);
    }

    public PlayCardModifier(int valueIn, AbstractCard absorbed) {
        super(valueIn);
        toPlayCard = absorbed.makeSameInstanceOf();
    }


    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        MultiCardPreview.add(card, toPlayCard);
    }

    @Override
    public void onExhausted(AbstractCard card) {
        Wiz.atb(new MakeTempCardInHandAction(toPlayCard.makeStatEquivalentCopy()));
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                CardModifierManager.removeSpecificModifier(card, PlayCardModifier.this, true);
            }
        });
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MadScienceModifiers")).TEXT[3] + toPlayCard.name + ".";
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractCard cpy = toPlayCard.makeSameInstanceOf();
        cpy.purgeOnUse = true;
        Wiz.atb(new NewQueueCardAction(cpy, true, true, true));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new PlayCardModifier(value, toPlayCard);
    }
}