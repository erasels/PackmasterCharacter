package thePackmaster.cardmodifiers.frostpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class RevertCostWhenPlayedMod extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("RevertCostWhenPlayedMod");


    private int originalCost;

    public RevertCostWhenPlayedMod(int costIn){
        this.originalCost = costIn;
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        card.modifyCostForCombat(originalCost);
        CardModifierManager.removeSpecificModifier(card, this, true);

    }



    public AbstractCardModifier makeCopy() {
        return new RevertCostWhenPlayedMod(originalCost);
    }
}
