package thePackmaster.util.creativitypack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
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
        AbstractDungeon.player.relics.stream().filter(r -> r instanceof onGenerateCardMidcombatInterface).forEach(r -> ((onGenerateCardMidcombatInterface) r).onCreateCard(c));
        AbstractDungeon.player.powers.stream().filter(r -> r instanceof onGenerateCardMidcombatInterface).forEach(r -> ((onGenerateCardMidcombatInterface) r).onCreateCard(c));
        AbstractDungeon.player.hand.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface) card).onCreateCard(c));
        AbstractDungeon.player.discardPile.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface) card).onCreateCard(c));
        AbstractDungeon.player.drawPile.group.stream().filter(card -> card instanceof onGenerateCardMidcombatInterface).forEach(card -> ((onGenerateCardMidcombatInterface) card).onCreateCard(c));
        AbstractDungeon.getMonsters().monsters.stream().filter(mon -> !mon.isDeadOrEscaped()).forEach(m -> m.powers.stream().filter(pow -> pow instanceof onGenerateCardMidcombatInterface).forEach(pow -> ((onGenerateCardMidcombatInterface) pow).onCreateCard(c)));
        if (c instanceof onGenerateCardMidcombatInterface) {
            ((onGenerateCardMidcombatInterface) c).onCreateThisCard();
        }
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
            retVal.add(tmpCard.makeCopy());
        }
        return retVal;
    }
}
