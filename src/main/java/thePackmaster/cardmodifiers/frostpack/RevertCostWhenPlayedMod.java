package thePackmaster.cardmodifiers.frostpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

public class RevertCostWhenPlayedMod extends AbstractCardModifier {
    public static String ID = SpireAnniversary5Mod.makeID("RevertCostWhenPlayedMod");


    private int originalCost;
    private boolean originalIsCostModified;

    public RevertCostWhenPlayedMod(int costIn, boolean isCostModified) {
        this.originalCost = costIn;
        this.originalIsCostModified = isCostModified;
    }

    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        card.modifyCostForCombat(originalCost);

        //Removing this mod on carduse must happen in an action or this will crash on comodification
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                CardModifierManager.removeSpecificModifier(card, RevertCostWhenPlayedMod.this, true);
                card.isCostModified = originalIsCostModified;
            }
        });

    }


    public AbstractCardModifier makeCopy() {
        return new RevertCostWhenPlayedMod(originalCost, originalIsCostModified);
    }
}
