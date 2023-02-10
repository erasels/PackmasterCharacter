package thePackmaster.cardmodifiers.madsciencepack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.RepeatCardAction;
import thePackmaster.util.Wiz;

@AbstractCardModifier.SaveIgnore
//SaveIgnore necessary due to the presence of AbstractCard. will crash on load without it.
public class ExpandModifier extends AbstractMadScienceModifier {


    public ExpandModifier() {
        super();
    }

    @Override
    public String identifier(AbstractCard card) {
        return "MadScienceExpandMod";
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        super.onInitialApplication(card);
        boolean wtfBasegame = card.costForTurn == 0;
        card.modifyCostForCombat(1);
        if(wtfBasegame) {
            card.costForTurn++;
            card.isCostModified = true;
        }
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return rawDescription + CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MadScienceModifiers")).TEXT[6];
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        AbstractCard c = card.makeStatEquivalentCopy();
        CardModifierManager.removeModifiersById(c, "MadScienceExpandMod", true);
        Wiz.atb(new RepeatCardAction(c));
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new ExpandModifier();
    }
}