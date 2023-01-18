package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractPinnacleCard extends AbstractPackmasterCard {

    public AbstractPinnacleCard(String cardID, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, AbstractCard.CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        setBackgroundTexture(
                "anniv5Resources/images/512/pinnaclepack/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/pinnaclepack/" + type.name().toLowerCase() + ".png"
        );
    }
    public AbstractPinnacleCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

}
