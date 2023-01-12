package thePackmaster.cards.pixiepack;

import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Locale;

public abstract class AbstractPixieCard extends AbstractPackmasterCard
{
    public AbstractPixieCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);
        setBackgroundTexture("anniv5Resources/images/512/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/pixie/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
    }
}
