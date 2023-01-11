package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.List;

public class PrismaticUtil {
    public static List<AbstractCard> getRandomDifferentColorCardInCombat(AbstractCard.CardType cardType, AbstractCard.CardRarity rarity, int amount) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard card : CardLibrary.cards.values()) {
            boolean isDifferentColor = card.color != AbstractDungeon.player.getCardColor();
            boolean isValidRarity = card.rarity == rarity || (rarity == null && (card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.UNCOMMON || card.rarity == AbstractCard.CardRarity.RARE));
            boolean isValidType = card.type == cardType || (cardType == null && card.type != AbstractCard.CardType.STATUS && card.type != AbstractCard.CardType.CURSE);
            boolean isUnlocked = Settings.treatEverythingAsUnlocked() || !UnlockTracker.isCardLocked(card.cardID);
            boolean isNotHealing = !card.hasTag(AbstractCard.CardTags.HEALING);
            if (isDifferentColor && isValidRarity && isValidType && isUnlocked && isNotHealing) {
                validCards.addToTop(card);
            }
        }

        List<AbstractCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(validCards.getRandomCard(AbstractDungeon.cardRandomRng).makeCopy());
        }

        return cards;
    }
}
