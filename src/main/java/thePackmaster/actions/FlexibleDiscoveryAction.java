package thePackmaster.actions;

import basemod.BaseMod;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;
import com.megacrit.cardcrawl.screens.CardRewardScreen;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToDiscardEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndAddToHandEffect;

import java.util.ArrayList;

public class FlexibleDiscoveryAction extends AbstractGameAction {
    private boolean retrieveCard = false;
    private boolean skippable;
    private final ArrayList<AbstractCard> cards;
    private boolean costsZeroThisTurn;
    private AbstractCardModifier cardModifier;

    public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, boolean costsZeroThisTurn) {
        this(cards, costsZeroThisTurn, null);
    }

    public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, boolean costsZeroThisTurn, AbstractCardModifier cardModifier) {
        this(cards, costsZeroThisTurn, false, cardModifier);
    }

    public FlexibleDiscoveryAction(ArrayList<AbstractCard> cards, boolean costsZeroThisTurn, boolean skippable, AbstractCardModifier cardModifier) {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        this.cards = cards;
        this.costsZeroThisTurn = costsZeroThisTurn;
        this.skippable = skippable;
        this.cardModifier = cardModifier;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            AbstractDungeon.cardRewardScreen.customCombatOpen(cards, CardRewardScreen.TEXT[1], skippable);
            this.tickDuration();
        } else {
            if (!this.retrieveCard) {
                if (AbstractDungeon.cardRewardScreen.discoveryCard != null) {
                    AbstractCard disCard = AbstractDungeon.cardRewardScreen.discoveryCard.makeStatEquivalentCopy();

                    if (AbstractDungeon.player.hasPower(MasterRealityPower.POWER_ID)) {
                        disCard.upgrade();
                    }

                    if (costsZeroThisTurn) {
                        disCard.setCostForTurn(0);
                    }
                    if (cardModifier!=null){
                        CardModifierManager.addModifier(disCard, cardModifier);
                    }
                    disCard.current_x = -1000.0F * Settings.scale;
                    if (AbstractDungeon.player.hand.size() < BaseMod.MAX_HAND_SIZE) {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToHandEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    } else {
                        AbstractDungeon.effectList.add(new ShowCardAndAddToDiscardEffect(disCard, (float)Settings.WIDTH / 2.0F, (float)Settings.HEIGHT / 2.0F));
                    }

                    AbstractDungeon.cardRewardScreen.discoveryCard = null;
                }

                this.retrieveCard = true;
            }

            this.tickDuration();
        }
    }
}
