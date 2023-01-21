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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class TransmuteCardAction extends AbstractGameAction {
    private static final String ID = SpireAnniversary5Mod.makeID("TransmuteCardAction");
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public static final String[] TEXT = uiStrings.TEXT;
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
        cards = amount;
        this.anyNumber = anyNumber;
        this.followup = followup;
        this.conditions = conditions;
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
            return;
        }
        if (!hooksTriggered) {
            hooksTriggered = true;
            for (AbstractPower power : AbstractDungeon.player.powers) {
                if (power instanceof TransmutableAffectingPower) {
                    ((TransmutableAffectingPower)power).modifyTransmuteAction(this);
                }
            }
            return;
        }
        if (!initialized) {
            initialized = true;
            //if another action does not clear this groups and I don't open the screen, the action will hang
            AbstractDungeon.handCardSelectScreen.selectedCards.clear();
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
            if (!transformPlayed) {
                if (AbstractDungeon.player.hand.size() < 1) {
                    isDone = true;
                } else if (AbstractDungeon.player.hand.size() <= cards && !anyNumber) {
                    AbstractDungeon.handCardSelectScreen.selectedCards.group.addAll(AbstractDungeon.player.hand.group);
                    AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = false;
                    return;
                } else {
                    AbstractDungeon.handCardSelectScreen.open(TEXT[0], Math.min(AbstractDungeon.player.hand.size(), cards), anyNumber, anyNumber);
                }
            } else {
                handleChoices(playedCard);
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
        }
        if (AbstractDungeon.gridSelectScreen.selectedCards.isEmpty() && AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty() && initialized) {
            finish();
        }
    }

    private void handleChoices(AbstractCard oldCard) {
        if (choices == 0) {
            isDone = true;
            System.out.println("TRANSMUTECARDACTION: Make 0 choices? How did this happen?");
        } else if (choices == 1) {
            AbstractCard newCard = getRandomCard(getTransmutationCandidates(oldCard)).makeCopy();
            UnlockTracker.markCardAsSeen(newCard.cardID);
            modifyNewCard(oldCard, newCard);
            if (transformPlayed) {
                modifyUsedCard(newCard);
            }
            transmutedPairs.put(oldCard, newCard);
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractDungeon.handCardSelectScreen.selectedCards.group.remove(oldCard);
                if (AbstractDungeon.handCardSelectScreen.selectedCards.group.isEmpty()) {
                    AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                }
            }
        } else {
            CardGroup tmp = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            ArrayList<AbstractCard> targets = getTransmutationCandidates(oldCard);
            for (int i = 0; i < choices; ++i) {
                if (targets.isEmpty()) {
                    targets = getTransmutationCandidates(oldCard);
                }
                AbstractCard choice = getRandomCard(targets);
                UnlockTracker.markCardAsSeen(choice.cardID);
                if (!tmp.contains(choice)) {
                    tmp.addToBottom(choice);
                    targets.remove(choice);
                }
            }
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            storedOldCard = oldCard;
            AbstractDungeon.gridSelectScreen.open(tmp, 1, TEXT[1], false);
        }
    }

    private void modifyUsedCard(AbstractCard newCard) {
        for (AbstractGameAction action : AbstractDungeon.actionManager.actions) {
            if (action instanceof UseCardAction) {
                AbstractCard targetCard = ReflectionHacks.getPrivate(action, UseCardAction.class, "targetCard");
                if (targetCard == playedCard) {
                    UseCardActionPatch.UseCardActionField.transmuteTargetCard.set(action, newCard);
                    AbstractDungeon.player.hand.removeCard(playedCard);
                    AbstractDungeon.player.cardInUse = null;
                }
            }
        }
        TransmuteCardEffect.copyCardPosition(playedCard, newCard);
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
            AbstractDungeon.topLevelEffects.add(new TransmuteCardEffect(transmutedPairs, transformPlayed ? null : CardGroup.CardGroupType.HAND, this, duration));
            completed = true;
        }
    }

    private ArrayList<AbstractCard> getTransmutationCandidates(AbstractCard oldCard) {
        ArrayList<AbstractCard> targets = new ArrayList<>();
        ArrayList<AbstractCard> group;
        switch (oldCard.rarity) {
            case RARE:
                group = AbstractDungeon.srcRareCardPool.group;
                break;
            case UNCOMMON:
                group = AbstractDungeon.srcUncommonCardPool.group;
                break;
            default:
                group = AbstractDungeon.srcCommonCardPool.group;
                break;
        }
        for (AbstractCard candidate : group) {
            if (!candidate.hasTag(AbstractCard.CardTags.HEALING) && (conditions == null || conditions.apply(candidate)) && !candidate.cardID.equals(oldCard.cardID)) {
                targets.add(candidate.makeCopy());
            }
        }
        return targets;
    }

    private AbstractCard getRandomCard(ArrayList<AbstractCard> targets) {
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
}
