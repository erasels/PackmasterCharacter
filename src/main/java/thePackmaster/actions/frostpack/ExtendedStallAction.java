package thePackmaster.actions.frostpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.madsciencepack.SimpleAddModifierAction;
import thePackmaster.cardmodifiers.frostpack.FrozenMod;
import thePackmaster.patches.psychicpack.occult.OccultPatch;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Comparator;

public class ExtendedStallAction extends AbstractGameAction {

    public ExtendedStallAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        ArrayList<AbstractCard> toFreeze = new ArrayList<>();
        ArrayList<AbstractCard> tempHand = new ArrayList<>(AbstractDungeon.player.hand.group);
        tempHand.removeIf(c -> CardModifierManager.hasModifier(c, FrozenMod.ID) || OccultPatch.isUnplayable(Wiz.p(), c) || c.cost == -1);
        tempHand.sort(Comparator.comparingInt(Wiz::getLogicalCardCost));
        for (int i = tempHand.size() - 1; i >= 0 && toFreeze.size() < amount; i--) {
            toFreeze.add(tempHand.get(i));
        }

        for(AbstractCard c : toFreeze) {
            addToTop(new SimpleAddModifierAction(new FrozenMod(), c, false));
        }
        isDone = true;
    }
}
