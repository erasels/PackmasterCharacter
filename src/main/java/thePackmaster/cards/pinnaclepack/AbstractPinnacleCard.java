package thePackmaster.cards.pinnaclepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractPinnacleCard extends AbstractPackmasterCard {

    public AbstractPinnacleCard(String cardID, int cost, AbstractCard.CardType type, AbstractCard.CardRarity rarity, AbstractCard.CardTarget target, AbstractCard.CardColor color) {
        super(cardID, cost, type, rarity, target, color, "pinnaclepack");
    }
    public AbstractPinnacleCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

}
