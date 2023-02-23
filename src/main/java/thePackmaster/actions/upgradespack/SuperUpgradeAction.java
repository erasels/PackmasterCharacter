package thePackmaster.actions.upgradespack;

import basemod.BaseMod;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.BranchingUpgradesCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import thePackmaster.SpireAnniversary5Mod;
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
        //SpireAnniversary5Mod.logger.info("====== Updating SuperUpgrade action, with list " + selectedCards);
        for (AbstractCard c : selectedCards) {
            for (int i = 0 ; i < amount ; i++) {
                forceUpgrade(c, true);
            }
        }
        isDone = true;
    }

    public static void forceUpgrade(AbstractCard card, boolean vfx) {
        if (card.canUpgrade()) {
            card.upgrade();
            if (vfx)
                AbstractDungeon.topLevelEffects.add(new LightUpgradeShineEffect(card.current_x, card.current_y));
            return;
        }

        AbstractCard exampleCard = card.makeCopy();

        int oldMagic = card.baseMagicNumber;

        int baseCost = exampleCard.cost;
        exampleCard.upgrade();
        int upgCostDiff = exampleCard.cost - baseCost;
        int turnCostDiff = card.costForTurn - card.cost;
        int targetCost = card.cost;
        if (targetCost >= 0 && upgCostDiff != 0) {
            targetCost += upgCostDiff;

            if (targetCost < 0)
                targetCost = 0;
        }

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

        if (card.cost > targetCost && card.cost >= 0) {
            card.cost = targetCost;
            card.costForTurn = targetCost + turnCostDiff;
            if (card.costForTurn < 0)
                card.costForTurn = 0;
            card.isCostModifiedForTurn = card.costForTurn != card.cost;
        }

        if (vfx)
            AbstractDungeon.topLevelEffects.add(new LightUpgradeShineEffect(card.current_x, card.current_y));

        if (card.timesUpgraded > 1) {
            card.name = card.originalName + "+" + card.timesUpgraded;
        }
        if (card.timesUpgraded < 1) {
            card.name = card.originalName + "*" + (-card.timesUpgraded);
        }
    }
}
