package thePackmaster.cards.intriguepack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractIntrigueCard extends AbstractPackmasterCard {
    public AbstractIntrigueCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        super(cardID, cost, type, rarity, target, "intrigue");

    }

    public AbstractIntrigueCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color, "intrigue");

    }

    public static boolean isMundane(AbstractCard c) {
        if (c.color == CardColor.CURSE)
            return false;

        if (c.rarity == CardRarity.COMMON || c.rarity == CardRarity.BASIC || c.rarity == CardRarity.SPECIAL)
            return true;
        return false;
    }

    public static boolean isPrecious(AbstractCard c) {
        if (c.color == CardColor.CURSE)
            return false;

        return c.rarity == CardRarity.UNCOMMON || c.rarity == CardRarity.RARE;
    }

    public static boolean isPromotable(AbstractCard c) {
        if (c.color == CardColor.CURSE)
            return false;

        return c.rarity == CardRarity.COMMON || c.rarity == CardRarity.BASIC || c.rarity == CardRarity.SPECIAL || c.rarity == CardRarity.UNCOMMON;
    }

    public static boolean promote(AbstractCard c) {
        if (c.color == CardColor.CURSE)
            return false;

        if (c.rarity == CardRarity.COMMON || c.rarity == CardRarity.BASIC || c.rarity == CardRarity.SPECIAL || c.rarity == CardRarity.UNCOMMON) {
            if (c.rarity == CardRarity.UNCOMMON)
                c.rarity = CardRarity.RARE;
            else
                c.rarity = CardRarity.UNCOMMON;

            c.superFlash(Color.GOLD.cpy());
            return true;
        }
        return false;
    }

    public static boolean demote(AbstractCard c) {
        if (c.color == CardColor.CURSE)
            return false;

        if (c.rarity == CardRarity.RARE) {
            c.rarity = CardRarity.UNCOMMON;
            c.superFlash(Color.GRAY.cpy());
            return true;
        }
        if (c.rarity == CardRarity.UNCOMMON) {
            c.rarity = CardRarity.COMMON;
            c.superFlash(Color.GRAY.cpy());
            return true;
        }

        return false;
    }

    public static boolean fullDemote(AbstractCard c) {
        if (c.color == CardColor.CURSE)
            return false;

        if (c.rarity == CardRarity.RARE || c.rarity == CardRarity.UNCOMMON) {
            c.rarity = CardRarity.COMMON;
            c.superFlash(Color.GRAY.cpy());
            return true;
        }

        return false;
    }

    @Override
    public void upp() {
    }
}
