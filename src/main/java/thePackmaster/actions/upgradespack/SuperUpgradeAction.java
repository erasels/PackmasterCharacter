package thePackmaster.actions.upgradespack;

import basemod.BaseMod;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.vfx.upgradespack.LightUpgradeShineEffect;

import java.util.ArrayList;
import java.util.List;

public class SuperUpgradeAction extends AbstractGameAction {

    private final List<AbstractCard> selectedCards = new ArrayList<>();

    public SuperUpgradeAction(List<AbstractCard> selectedCards, int amount) {
        this.amount = amount;
        this.selectedCards.addAll(selectedCards);
    }

    @Override
    public void update() {
        BaseMod.logger.info("====== Updating SuperUpgrade action, with list " + selectedCards);
        for (AbstractCard c : selectedCards) {
            for (int i = 0 ; i < amount ; i++) {
                forceUpgrade(c);
            }
        }
        isDone = true;
    }

    private void forceUpgrade(AbstractCard card) {
        if (card.canUpgrade()) {
            card.upgrade();
            AbstractDungeon.topLevelEffects.add(new LightUpgradeShineEffect(card.current_x, card.current_y));
            return;
        }

        int oldMagic = card.baseMagicNumber;
        int oldCost = card.cost;


        int originalCost = CardLibrary.getCard(card.cardID).cost;
        int diff = card.costForTurn - card.cost;
        BaseMod.logger.info("Original cost of " + card + " is " + originalCost);

        card.upgraded = false;
        card.upgrade();
        if (card.baseMagicNumber < 1) {
            card.baseMagicNumber = card.magicNumber = oldMagic;
        }

        BaseMod.logger.info("Upgraded cost of " + card + " is " + card.cost);


        int costReduction = originalCost - card.cost;
        BaseMod.logger.info("Cost reduction of " + card + " is " + costReduction);
        if (oldCost >= 0 && oldCost - costReduction >= 0) {
            BaseMod.logger.info("Changing cost to "+ (oldCost - costReduction));
            int newBaseCost = oldCost - costReduction;


            card.cost = newBaseCost;// 873
            if (card.costForTurn > 0) {// 875
                card.costForTurn = newBaseCost + diff;// 876
            }

            if (card.costForTurn < 0) {// 878
                card.costForTurn = 0;// 879
            }

            card.upgradedCost = true;
        }

        AbstractDungeon.topLevelEffects.add(new LightUpgradeShineEffect(card.current_x, card.current_y));
        if (card.timesUpgraded > 1) {
            card.name = card.originalName + "+" + card.timesUpgraded;
        }
    }
}
