package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.fueledpack.GrittyAsh;
import thePackmaster.cards.fueledpack.HotAsh;
import thePackmaster.cards.fueledpack.PowderyAsh;
import thePackmaster.powers.fueledpack.EmbersPower;
import thePackmaster.powers.fueledpack.PhoenixHeartPower;
import thePackmaster.vfx.fueledpack.ConsumeCardEffect;

import java.util.ArrayList;
import java.util.HashMap;

import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class ConsumeCardsAction extends AbstractGameAction {
    private ConsumeCardEffect effect;
    private final HashMap<AbstractCard, AbstractCard> consumePairs = new HashMap<>();
    private static final float DURATION = Settings.ACTION_DUR_FAST;
    private boolean started = false;

    public ConsumeCardsAction(AbstractCard oldCard) {
        AbstractCard newCard = getAshesCard(oldCard);
        AbstractPower pow = adp().getPower(EmbersPower.POWER_ID);
        if (pow != null)
            for (int i = 0; i < pow.amount; i++)
                newCard.upgrade();

        consumePairs.put(oldCard, newCard);
        duration = DURATION;
    }

    public ConsumeCardsAction(ArrayList<AbstractCard> oldCards) {
        for (AbstractCard c : oldCards) {
            AbstractCard newCard = getAshesCard(c);
            AbstractPower pow = adp().getPower(EmbersPower.POWER_ID);
            if (pow != null)
                for (int i = 0; i < pow.amount; i++) {
                    if (newCard != null)
                        newCard.upgrade();
                }

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
            AbstractPower pow = adp().getPower(PhoenixHeartPower.POWER_ID);
            for (AbstractCard c : consumePairs.keySet()) {
                if (pow instanceof PhoenixHeartPower)
                    ((PhoenixHeartPower) pow).onConsume();
                if (consumePairs.get(c) != null) {
                    adp().hand.removeCard(c);
                    copyCardPosition(c, consumePairs.get(c));
                } else {
                    att(new ExhaustSpecificCardAction(c, adp().hand, true));
                    consumePairs.remove(c);
                }
            }

            if (consumePairs.size() < 2)
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
        if (oldCard.hasTag(SpireAnniversary5Mod.FUEL))
            return oldCard.makeCopy();
        else if (oldCard.type == AbstractCard.CardType.ATTACK)
            return new HotAsh();
        else if (oldCard.type == AbstractCard.CardType.POWER)
            return new PowderyAsh();
        else if (oldCard.type == AbstractCard.CardType.SKILL)
            return new GrittyAsh();
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