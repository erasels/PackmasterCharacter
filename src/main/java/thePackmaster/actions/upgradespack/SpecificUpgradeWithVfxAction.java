package thePackmaster.actions.upgradespack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.vfx.upgradespack.LightUpgradeShineEffect;

public class SpecificUpgradeWithVfxAction extends AbstractGameAction {

    private final AbstractCard[] cards;

    public SpecificUpgradeWithVfxAction(AbstractCard ... cards) {
        this.cards = cards;
    }

    @Override
    public void update() {
        for (AbstractCard card : cards) {
            if (card.canUpgrade()) {
                //if (AbstractDungeon.player.hand.contains(card)) {
                    AbstractDungeon.topLevelEffects.add(new LightUpgradeShineEffect(card.current_x, card.current_y));
                //}
                card.upgrade();
            }
        }
        isDone = true;
    }
}
