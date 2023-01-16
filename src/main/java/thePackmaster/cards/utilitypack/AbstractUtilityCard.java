package thePackmaster.cards.utilitypack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Locale;

public abstract class AbstractUtilityCard extends AbstractPackmasterCard
{
    public AbstractUtilityCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture("anniv5Resources/images/512/utility/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/utility/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
    }
}
