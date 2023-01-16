package thePackmaster.cards.boardgamepack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBoardCard extends AbstractPackmasterCard {

    public boolean reroll = false, isChance = false;

    public AbstractBoardCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "boardgame/board-back-");

    }
}
