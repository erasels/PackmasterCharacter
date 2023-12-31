package thePackmaster.cards.farmerpack;


import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;

import java.util.Iterator;
import java.util.Locale;

public abstract class AbstractFarmerCard extends AbstractPackmasterCard {
    public AbstractFarmerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);
        if (!SpireAnniversary5Mod.oneFrameMode) {
            setBackgroundTexture(
                    "anniv5Resources/images/512/farmer/" + type.name().toLowerCase(Locale.ROOT) + ".png",
                    "anniv5Resources/images/1024/farmer/" + type.name().toLowerCase(Locale.ROOT) + ".png"
            );
        }
    }

    public int checkTypes(boolean cardIsBeingPlayed){
        int numToCheck = Math.max(0, AbstractDungeon.actionManager.cardsPlayedThisTurn.size() - (cardIsBeingPlayed ? 1 : 0));
        return (int)AbstractDungeon.actionManager.cardsPlayedThisTurn.subList(0, numToCheck).stream()
                .map(c -> c.type)
                .distinct()
                .count();
    }

    public AbstractFarmerCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);

    }
}
