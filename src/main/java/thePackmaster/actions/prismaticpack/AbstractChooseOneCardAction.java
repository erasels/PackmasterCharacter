package thePackmaster.actions.prismaticpack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

public abstract class AbstractChooseOneCardAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private final boolean zeroCost;
    private final Integer costReduction;

    public AbstractChooseOneCardAction(int amount, boolean zeroCost, Integer costReduction) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.amount = amount;
        this.zeroCost = zeroCost;
        this.costReduction = costReduction;
    }

    public void update() {
        ArrayList<AbstractCard> generatedCards = this.generateCardChoices();

        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(generatedCards, CardRewardScreen.TEXT[1], true);
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    for (int i = 0; i < this.amount; i++) {
                        AbstractCard card = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();
                        if (AbstractDungeon.player.hasPower(MasterRealityPower.POWER_ID)) {
                            card.upgrade();
                        }

                        if (this.zeroCost) {
                            card.setCostForTurn(0);
                        }
                        else if (this.costReduction != null && this.costReduction > 0) {
                            if (card.cost > 0) {
                                card.setCostForTurn(Math.max(card.cost - this.costReduction, 0));
                            }
                        }
                        card.current_x = -1000.0F * Settings.xScale + i * AbstractCard.IMG_WIDTH;
                        int halfWidths = -this.amount + i * 2 + 1;
                        float x = Settings.WIDTH / 2.0F - halfWidths * AbstractCard.IMG_WIDTH / 2.0F;
                        float y = Settings.HEIGHT / 2.0F;
                        if (AbstractDungeon.player.hand.size() + i + 1 <= BaseMod.MAX_HAND_SIZE) {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(card, x, y));
                        }
                        else {
                            AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(card, x, y));
                        }

                    }
                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }
                this.retrieveCard = true;
            }
        }
        this.tickDuration();
    }

    protected abstract ArrayList<AbstractCard> generateCardChoices();
}
