package thePackmaster.cards.shamanpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Locale;

public abstract class AbstractShamanCard extends AbstractPackmasterCard
{
    public AbstractShamanCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target)
    {
        super(cardID, cost, type, rarity, target);

        if (!SpireAnniversary5Mod.oneFrameMode)
        setBackgroundTexture("anniv5Resources/images/512/shaman/" + type.name().toLowerCase(Locale.ROOT)+".png",
                "anniv5Resources/images/1024/shaman/" + type.name().toLowerCase(Locale.ROOT)+".png");
    }

    @Override
    public void upp() {
    }
}
