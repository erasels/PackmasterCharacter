package thePackmaster.actions.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.util.Wiz;

public class ScrapMetalAction extends AbstractGameAction {

    private final int blockPerUpgrade;

    public ScrapMetalAction(int amount) {
        blockPerUpgrade = amount;
    }

    @Override
    public void update() {
        int upgrades = AbstractDungeon.player.currentBlock / blockPerUpgrade;
        CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        group.group.addAll(Wiz.adp().hand.group);
        group.group.addAll(Wiz.adp().drawPile.group);
        group.group.addAll(Wiz.adp().discardPile.group);

        addToBot(new RandomUpgradeWithVfxAction(upgrades,group));

        isDone = true;
    }
}
