package thePackmaster.cards.boardgamepack;

import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractBoardCard extends AbstractPackmasterCard {

    public boolean reroll = false, isChance = false;

    public AbstractBoardCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture(
                "anniv5Resources/images/512/boardgame/board-back-" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/boardgame/board-back-" + type.name().toLowerCase() + ".png"
        );
    }
}
