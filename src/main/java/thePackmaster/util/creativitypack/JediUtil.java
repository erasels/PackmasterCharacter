package thePackmaster.util.creativitypack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import java.util.ArrayList;
import java.util.function.Predicate;

public class JediUtil {
    public static ArrayList<AbstractCard> cardsCreatedThisTurn = new ArrayList<>();
    public static ArrayList<AbstractCard> cardsCreatedThisCombat = new ArrayList<>();

    public static void receiveOnPlayerTurnStart() {
        cardsCreatedThisTurn.clear();
    }

    public static void receiveOnBattleStart(AbstractRoom abstractRoom) {
        cardsCreatedThisTurn.clear();
        cardsCreatedThisCombat.clear();
    }

    public static void onGenerateCardMidcombat(AbstractCard c) {
        JediUtil.cardsCreatedThisCombat.add(c);
        JediUtil.cardsCreatedThisTurn.add(c);
    }

    public static CardGroup filterCardsForDiscovery(Predicate<AbstractCard> filter) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        CardLibrary.getAllCards().stream().filter(filter).forEach(c -> retVal.addToTop(c.makeCopy()));
        return retVal;
    }

    public static CardGroup filterCardsForDiscovery(ArrayList<AbstractCard> list, Predicate<AbstractCard> filter) {
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        list.stream().filter(filter).forEach(c -> retVal.addToTop(c.makeCopy()));
        return retVal;
    }

    public static ArrayList<AbstractCard> createCardsForDiscovery(CardGroup list) {
        if (list.size() < 4) {
            return list.group;
        }

        ArrayList<AbstractCard> retVal = new ArrayList<>();
        while (retVal.size() < 3) {
            AbstractCard tmpCard = list.getRandomCard(true);
            list.removeCard(tmpCard);
            AbstractCard copy = tmpCard.makeCopy();
            if (tmpCard.upgraded) {
                tmpCard.upgrade();
            }
            retVal.add(tmpCard);
        }
        return retVal;
    }
}
