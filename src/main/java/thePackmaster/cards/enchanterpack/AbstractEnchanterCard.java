package thePackmaster.cards.enchanterpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Locale;

public abstract class AbstractEnchanterCard extends AbstractPackmasterCard {

    public AbstractEnchanterCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, CardColor color) {
        super(cardID, cost, type, rarity, target, color);

        if (!SpireAnniversary5Mod.oneFrameMode) {
            setBackgroundTexture(
                    "anniv5Resources/images/512/enchanter/" + type.name().toLowerCase(Locale.ROOT) + ".png",
                    "anniv5Resources/images/1024/enchanter/" + type.name().toLowerCase(Locale.ROOT) + ".png"
            );
        }
    }

    public AbstractEnchanterCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target) {
        this (cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW);
    }

    public ArrayList<AbstractCard> getNeighbors() {
        ArrayList<AbstractCard> neighbors = new ArrayList<>();
        if (Wiz.hand().contains(this)) {
            int index = Wiz.hand().group.indexOf(this);
            if (index > 0) {
                neighbors.add(Wiz.hand().group.get(index -1));
            }
            if (index < Wiz.hand().size() - 1) {
                neighbors.add(Wiz.hand().group.get(index + 1));
            }
        }
        return neighbors;
    }

    public void onPlayedNeighbor(AbstractCard playedCard, AbstractMonster monster) {
    }
}
