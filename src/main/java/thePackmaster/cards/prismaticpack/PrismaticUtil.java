package thePackmaster.cards.prismaticpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import thePackmaster.ThePackmaster;

import java.util.ArrayList;
import java.util.List;

public class PrismaticUtil {
    public static List<AbstractCard> getRandomDifferentColorCardInCombat(AbstractCard.CardType cardType, int amount) {
        CardGroup validCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

        for (AbstractCard card : CardLibrary.cards.values()) {
            boolean isDifferentColor = card.color != ThePackmaster.Enums.PACKMASTER_RAINBOW;
            boolean isValidRarity = card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.UNCOMMON || card.rarity == AbstractCard.CardRarity.RARE;
            boolean isValidType = card.type == cardType || (cardType == null && card.type != AbstractCard.CardType.STATUS && card.type != AbstractCard.CardType.CURSE);
            boolean isUnlocked = Settings.treatEverythingAsUnlocked() || !UnlockTracker.isCardLocked(card.cardID);
            if (isDifferentColor && isValidRarity && isValidType && isUnlocked) {
                validCards.addToTop(card);
            }
        }

        List<AbstractCard> cards = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            cards.add(validCards.getRandomCard(AbstractDungeon.cardRandomRng));
        }

        return cards;
    }
}
