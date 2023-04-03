package thePackmaster.actions.transmutationpack;

import basemod.ReflectionHacks;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;
import thePackmaster.cardmodifiers.transmutationpack.PurityModifier;
import thePackmaster.cards.transmutationpack.TransmutableCard;
import thePackmaster.patches.transmutationpack.UseCardActionPatch;
import thePackmaster.powers.transmutationpack.TransmutableAffectingPower;
import thePackmaster.vfx.transmutationpack.TransmuteCardEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TransmuteCardAction extends AbstractGameAction {
    private static final String ID = SpireAnniversary5Mod.makeID("TransmuteCardAction");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;
    private static final HashMap<AbstractCard.CardRarity, Integer> RARITY_VALUES = makeRarityMap();
    private boolean initialized = false;
    private boolean hooksTriggered = false;
    private boolean completed = false;
    public int choices = 1;
    public int cards;
    public int purity = 1;
    private final boolean anyNumber;
    private final BiConsumer<AbstractCard, AbstractCard> followup;
    private AbstractCard storedOldCard;
    private final Function<AbstractCard, Boolean> conditions;
    private boolean transformPlayed = false;
    private AbstractCard playedCard;
    private final HashMap<AbstractCard, AbstractCard> transmutedPairs = new HashMap<>();
    private TransmuteCardEffect effect;
    public int rarityModifier;
    
    private static HashMap<AbstractCard.CardRarity, Integer> makeRarityMap() {
        HashMap<AbstractCard.CardRarity, Integer> retVal = new HashMap<>();
        retVal.put(AbstractCard.CardRarity.COMMON, 1);
        retVal.put(AbstractCard.CardRarity.UNCOMMON, 2);
        retVal.put(AbstractCard.CardRarity.RARE, 3);
        return retVal;
    }
    
    public TransmuteCardAction() {
        this(1);
    }
    
    public TransmuteCardAction(int amount) {
        this(amount, amount > 1);
    }
    
    public TransmuteCardAction(int amount, boolean anyNumber) {
        this(amount, anyNumber, null);
    }
    
    public TransmuteCardAction(int amount, boolean anyNumber, BiConsumer<AbstractCard, AbstractCard> followup) {
        this(amount, anyNumber, followup, null);
    }
    
    public TransmuteCardAction(int amount, boolean anyNumber, BiConsumer<AbstractCard, AbstractCard> followup, Function<AbstractCard, Boolean> conditions) {
        this(amount, anyNumber, followup, conditions, 0);
    }
    
    public TransmuteCardAction(int amount, boolean anyNumber, BiConsumer<AbstractCard, AbstractCard> followup, Function<AbstractCard, Boolean> conditions, int rarityModifier) {
        cards = amount;
        this.anyNumber = anyNumber;
        this.followup = followup;
        this.conditions = conditions;
        this.rarityModifier = rarityModifier;
    }
    
    public TransmuteCardAction(boolean anyNumber) {
        this(anyNumber ? 99 : 1, anyNumber);
    }
    
    public TransmuteCardAction(BiConsumer<AbstractCard, AbstractCard> followup) {
        this(1, false, followup);
    }
    
    public TransmuteCardAction(Function<AbstractCard, Boolean> conditions) {
        this(1, false, null, conditions);
    }
    
    public TransmuteCardAction(AbstractCard playedCard) {
        this();
        this.playedCard = playedCard;
        transformPlayed = true;
    }
    
    public void update() {
        if (completed) {
            if (effect.isDone || !AbstractDungeon.topLevelEffects.contains(effect)) {
                isDone = true;
            }
        } else if (!hooksTriggered) {
            hooksTriggered = true;
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if (power instanceof TransmutableAffectingPower) {
                    ((TransmutableAffectingPower)power).modifyTransmuteAction(this);
                }
            }
            return;
        } else if (!initialized) {
            initialized = true;
            //if another action does not clear this groups and I don't open the screen, the action will hang
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            if (!transformPlayed) {
                if (AbstractDungeon.player.hand.size() < 1) {
                    isDone = true;
                } else if (AbstractDungeon.player.hand.size() <= cards && !anyNumber) {
                    AbstractDungeon.handCardSelectScreen.selectedCards.group.addAll(AbstractDungeon.player.hand.group);
                    AbstractDungeon.player.releaseCard();
                    AbstractDungeon.player.hand.stopGlowing();
                    AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = false;
                } else {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], Math.min(AbstractDungeon.player.hand.size(), cards), anyNumber, anyNumber);
                }
            } else {
                handleChoices(playedCard);
            }
            return;
        } else if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
            AbstractCard newCard = AbstractDungeon.gridSelectScreen.selectedCards.get(0).makeCopy();
            if (!transformPlayed) {
                modifyNewCard(storedOldCard, newCard);
                transmutedPairs.put(storedOldCard, newCard);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                AbstractDungeon.handCardSelectScreen.selectedCards.group.remove(0);
                if (!AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                    AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = false;
                }
            } else {
                modifyNewCard(playedCard, newCard);
                AbstractDungeon.gridSelectScreen.selectedCards.clear();
                modifyUsedCard(newCard);
                transmutedPairs.put(playedCard, newCard);
                finish();
            }
            return;
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved && !transformPlayed) {
            if (AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                return;
            }
            AbstractCard oldCard = AbstractDungeon.handCardSelectScreen.selectedCards.group.get(0);
            AbstractDungeon.player.hand.group.remove(oldCard);
            handleChoices(oldCard);
            return;
        } else {
            finish();
        }
        if (isDone) {
            moveCards();
        }
    }
    
    private void handleChoices(AbstractCard oldCard) {
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractDungeon.handCardSelectScreen.selectedCards.removeCard(oldCard);
            if (AbstractDungeon.handCardSelectScreen.selectedCards.isEmpty()) {
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }
        }
        if (choices == 0) {
            isDone = true;
            System.out.println("TRANSMUTECARDACTION: Make 0 choices? How did this happen?");
        } else if (choices == 1) {
            AbstractCard newCard = getRandomCard(getTransmutationCandidates(oldCard));
            if (newCard != null) {
                newCard = newCard.makeCopy();
                UnlockTracker.markCardAsSeen(newCard.cardID);
                modifyNewCard(oldCard, newCard);
                if (transformPlayed) {
                    modifyUsedCard(newCard);
                }
            }
            transmutedPairs.put(oldCard, newCard);
        } else {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            ArrayList<AbstractCard> targets = getTransmutationCandidates(oldCard);
            for (int i = 0; i < choices; ++i) {
                if (targets.isEmpty()) {
                    targets = getTransmutationCandidates(oldCard);
                }
                AbstractCard choice = getRandomCard(targets);
                if (choice != null) {
                    UnlockTracker.markCardAsSeen(choice.cardID);
                    if (!tmp.contains(choice)) {
                        tmp.addToBottom(choice);
                        targets.remove(choice);
                    }
                }
            }
            storedOldCard = oldCard;
            if (tmp.size() > 1) {
                AbstractDungeon.gridSelectScreen.open(tmp, 1, TEXT[1], false);
            } else {
                transmutedPairs.put(oldCard, getRandomCard(tmp.group));
            }
        }
    }
    
    private void modifyUsedCard(AbstractCard newCard) {
        for (AbstractGameAction action : AbstractDungeon.actionManager.actions) {
            if (action instanceof UseCardAction) {
                AbstractCard targetCard = ReflectionHacks.getPrivate(action, UseCardAction.class, "targetCard");
                if (targetCard == playedCard) {
                    UseCardActionPatch.UseCardActionField.transmuteTargetCard.set(action, newCard);
                }
            }
        }
        copyCardPosition(playedCard, newCard);
    }
    
    private void finish() {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof TransmutableAffectingPower) {
                ((TransmutableAffectingPower)power).onTransmute(transmutedPairs);
            }
        }
        float duration = 0.75f;
        if (transmutedPairs.keySet().size() > 1) {
            duration *= 1.5f;
        }
        if (transmutedPairs.isEmpty()) {
            isDone = true;
        } else {
            effect = new TransmuteCardEffect(transmutedPairs, duration);
            AbstractDungeon.topLevelEffects.add(effect);
            completed = true;
        }
    }
    
    private ArrayList<AbstractCard> getTransmutationCandidates(AbstractCard oldCard) {
        ArrayList<AbstractCard> targets = new ArrayList<>();
        if (shouldCardTransmute(oldCard)) {
            ArrayList<AbstractCard> group = getPool(oldCard);
            for (AbstractCard candidate : group) {
                if (!candidate.hasTag(AbstractCard.CardTags.HEALING) && (conditions == null || conditions.apply(candidate)) && !candidate.cardID.equals(oldCard.cardID)) {
                    targets.add(candidate.makeCopy());
                }
            }
        }
        return targets;
    }

    private boolean shouldCardTransmute(AbstractCard card) {
        return !transformPlayed || (!card.exhaust && card.type != AbstractCard.CardType.POWER);
    }
    
    private ArrayList<AbstractCard> getPool(AbstractCard oldCard) {
        int rarityValue = RARITY_VALUES.getOrDefault(oldCard.rarity, 1);
        rarityValue = modifyRarity(rarityValue);
        rarityValue = Math.max(1, Math.min(3, rarityValue));
        switch (rarityValue) {
            default:
                return AbstractDungeon.srcCommonCardPool.group;
            case 2:
                return AbstractDungeon.srcUncommonCardPool.group;
            case 3:
                return AbstractDungeon.srcRareCardPool.group;
        }
    }
    
    private int modifyRarity(int currentValue) {
        return currentValue + rarityModifier;
    }
    
    private AbstractCard getRandomCard(ArrayList<AbstractCard> targets) {
        if (targets.isEmpty()) {
            return null;
        }
        return targets.get(AbstractDungeon.cardRandomRng.random(targets.size() - 1));
    }
    
    private void modifyNewCard(AbstractCard oldCard, AbstractCard newCard) {
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof TransmutableAffectingPower) {
                ((TransmutableAffectingPower)power).affectTransmutedCard(newCard);
            }
        }
        if (followup != null) {
            followup.accept(oldCard, newCard);
        }
        if (oldCard instanceof TransmutableCard) {
            ((TransmutableCard) oldCard).onTransmuted(newCard);
        }
        PurityModifier mod = new PurityModifier(purity);
        CardModifierManager.addModifier(newCard, mod);
        if (oldCard instanceof TransmutableCard) {
            ((TransmutableCard)oldCard).getMutableAbilities().forEach(effect -> {
                CardModifierManager.addModifier(newCard, effect);
            });
        }
        for (AbstractCardModifier effect : CardModifierManager.modifiers(oldCard)) {
            if (effect instanceof AbstractExtraEffectModifier) {
                if (((AbstractExtraEffectModifier)effect).isMutable) {
                    CardModifierManager.addModifier(newCard, effect);
                    ((AbstractExtraEffectModifier) effect).onCardTransmuted(oldCard, newCard);
                }
            }
            if (effect instanceof PurityModifier) {
                CardModifierManager.addModifier(newCard, effect.makeCopy());
            }
        }
        newCard.applyPowers();
    }
    
    private void moveCards() {
        if (!transformPlayed) {
            for (AbstractCard card : transmutedPairs.keySet()) {
                AbstractCard newCard = transmutedPairs.get(card);
                if (newCard != null) {
                    copyCardPosition(card, newCard);
                    AbstractDungeon.player.hand.addToHand(newCard);
                }
            }
            AbstractDungeon.player.hand.refreshHandLayout();
        } else {
            for (AbstractCard card : transmutedPairs.keySet()) {
                AbstractCard newCard = transmutedPairs.get(card);
                if (newCard != null) {
                    copyCardPosition(card, newCard);
                }
            }
        }
        AbstractDungeon.player.hand.applyPowers();
        AbstractDungeon.player.hand.glowCheck();
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