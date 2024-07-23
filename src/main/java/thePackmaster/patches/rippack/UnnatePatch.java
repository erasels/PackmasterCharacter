package thePackmaster.patches.rippack;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.ArrayList;

public class UnnatePatch {
    @SpirePatch(clz = CardGroup.class, method = "initializeDeck")
    public static class UnnateDeckInitializePatch
    {
        @SpireInsertPatch(
                rloc = 6,
                localvars = {"copy"}
        )
        public static void initializeDeckPatch(CardGroup combatDeck, @ByRef CardGroup[] copy)
        {
            ArrayList<AbstractCard> placeOnBot = new ArrayList<>();

            for (AbstractCard newCard : copy[0].group) {
                if (newCard instanceof AbstractPackmasterCard) {
                    if (((AbstractPackmasterCard) newCard).isUnnate)
                        placeOnBot.add(newCard);
                }
            }
            if (placeOnBot.size() > 0) {
                for (AbstractCard abstractCard : placeOnBot) {
                    copy[0].removeCard(abstractCard);
                    copy[0].addToBottom(abstractCard);
                }
            }
        }
    }
}
