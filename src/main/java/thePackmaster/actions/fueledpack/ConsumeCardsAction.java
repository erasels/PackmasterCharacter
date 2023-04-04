package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.fueledpack.BurningSword;
import thePackmaster.cards.fueledpack.HotAsh;
import thePackmaster.powers.fueledpack.ControlledBurnPower;
import thePackmaster.vfx.fueledpack.ConsumeCardEffect;

import java.util.ArrayList;
import java.util.HashMap;

import static thePackmaster.util.Wiz.adp;

public class ConsumeCardsAction extends AbstractGameAction {
    private ConsumeCardEffect effect;
    private final HashMap<AbstractCard, AbstractCard> consumePairs = new HashMap<>();
    private static final float DURATION = Settings.ACTION_DUR_FAST;
    private boolean started = false;

    public ConsumeCardsAction(AbstractCard oldCard) {
        AbstractCard newCard = getAshesCard(oldCard);
        consumePairs.put(oldCard, newCard);
        duration = DURATION;
    }

    public ConsumeCardsAction(ArrayList<AbstractCard> oldCards) {
        for (AbstractCard c : oldCards) {
            AbstractCard newCard = getAshesCard(c);

            consumePairs.put(c, newCard);
        }
        duration = DURATION;
    }
    
    public void update() {
        if (consumePairs.size() == 0) {
            isDone = true;
            return;
        }

        if (!started) {
            started = true;
            AbstractPower pow = adp().getPower(ControlledBurnPower.POWER_ID);
            ArrayList<AbstractCard> removeList = new ArrayList<>();
            for (AbstractCard c : consumePairs.keySet()) {
                if (pow instanceof ControlledBurnPower)
                    ((ControlledBurnPower) pow).onConsume();
                if (consumePairs.get(c) != null) {
                    adp().hand.removeCard(c);
                    copyCardPosition(c, consumePairs.get(c));
                } else {
                    adp().hand.moveToExhaustPile(c);
                    removeList.add(c);
                }
            }

            for (AbstractCard c : removeList)
                consumePairs.remove(c);

            if (consumePairs.size() == 0) {
                isDone = true;
                return;
            }
            else if (consumePairs.size() == 1)
                effect = new ConsumeCardEffect(consumePairs, 0.75f);
            else
                effect = new ConsumeCardEffect(consumePairs, 1.25f);
            AbstractDungeon.topLevelEffects.add(effect);
        } else if (effect.isDone || !AbstractDungeon.topLevelEffects.contains(effect)) {
            for (AbstractCard card : consumePairs.keySet()) {
                AbstractCard newCard = consumePairs.get(card);
                if (newCard != null) {
                    copyCardPosition(card, newCard);
                    adp().hand.addToHand(newCard);
                }
            }
            AbstractDungeon.player.hand.refreshHandLayout();
            adp().hand.applyPowers();
            adp().hand.glowCheck();
            isDone = true;
        }
    }

    private static AbstractCard getAshesCard(AbstractCard oldCard) {
        if (oldCard instanceof HotAsh)
            return null;
        else if (oldCard instanceof BurningSword)
            return oldCard.makeCopy();
        else if (oldCard.type == AbstractCard.CardType.ATTACK
                || oldCard.type == AbstractCard.CardType.SKILL
                || oldCard.type == AbstractCard.CardType.POWER)
            return new HotAsh();
        else
            return null;
    }
    
    public static void copyCardPosition(AbstractCard original, AbstractCard target) {
        target.current_x = original.current_x;
        target.current_y = original.current_y;
        target.target_x = original.target_x;
        target.target_y = original.target_y;
        target.drawScale = original.drawScale;
        target.targetDrawScale = original.targetDrawScale;
        target.angle = original.angle;
        target.targetAngle = original.targetAngle;
    }
}