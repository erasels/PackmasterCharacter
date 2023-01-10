package thePackmaster.actions.upgradespack;

import basemod.BaseMod;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
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

        card.upgraded = false;
        if (card instanceof BranchingUpgradesCard) {
            BranchingUpgradesCard c = (BranchingUpgradesCard)card;
            if (c.isBranchUpgrade()) {
                c.doBranchUpgrade();
            } else {
                c.doNormalUpgrade();
            }
        } else {
            card.upgrade();
        }
        if (card.baseMagicNumber < 1) {
            card.baseMagicNumber = card.magicNumber = oldMagic;
        }



        int costReduction = originalCost - card.cost;
        if (oldCost >= 0 && oldCost - costReduction >= 0) {
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
        if (card.timesUpgraded < 1) {
            card.name = card.originalName + "*" + (-card.timesUpgraded);
        }
    }

    public static void silentForceUpgrade(AbstractCard card) {
        if (card.canUpgrade()) {
            card.upgrade();
            return;
        }

        int oldMagic = card.baseMagicNumber;
        int oldCost = card.cost;

        int originalCost = CardLibrary.getCard(card.cardID).cost;
        int diff = card.costForTurn - card.cost;

        card.upgraded = false;
        if (card instanceof BranchingUpgradesCard) {
            BranchingUpgradesCard c = (BranchingUpgradesCard)card;
            if (c.isBranchUpgrade()) {
                c.doBranchUpgrade();
            } else {
                c.doNormalUpgrade();
            }
        } else {
            card.upgrade();
        }
        if (card.baseMagicNumber < 1) {
            card.baseMagicNumber = card.magicNumber = oldMagic;
        }


        int costReduction = originalCost - card.cost;
        if (oldCost >= 0 && oldCost - costReduction >= 0) {
            int newBaseCost = oldCost - costReduction;


            card.cost = newBaseCost;
            if (card.costForTurn > 0) {
                card.costForTurn = newBaseCost + diff;
            }

            if (card.costForTurn < 0) {
                card.costForTurn = 0;
            }

            card.upgradedCost = true;
        }

        if (card.timesUpgraded > 1) {
            card.name = card.originalName + "+" + card.timesUpgraded;
        }
        if (card.timesUpgraded < 1) {
            card.name = card.originalName + "*" + (-card.timesUpgraded);
        }
    }
}
