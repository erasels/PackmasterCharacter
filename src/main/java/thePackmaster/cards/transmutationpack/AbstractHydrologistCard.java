package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.transmutationpack.HydrologistDamageAction;
import thePackmaster.cards.AbstractPackmasterCard;

public abstract class AbstractHydrologistCard extends AbstractPackmasterCard {
    private final Subtype subtype;

    public AbstractHydrologistCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, Subtype subtype) {
        super(cardID, cost, type, rarity, target);

        if (!SpireAnniversary5Mod.oneFrameMode) {
            setBackgroundTexture(
                    "anniv5Resources/images/512/hydrologist/" + type.name().toLowerCase() + ".png",
                    "anniv5Resources/images/1024/hydrologist/" + type.name().toLowerCase() + ".png"
            );
            setOrbTexture(
                    "anniv5Resources/images/512/hydrologist/orb.png",
                    "anniv5Resources/images/1024/hydrologist/orb.png"
            );
        }
        this.subtype = subtype;
    }

    protected void hydrologistDamage(AbstractPlayer source, AbstractMonster target, int damage) {
        addToBot(new HydrologistDamageAction(getSubtype(), target, new DamageInfo(source, damage)));
    }

    public Subtype getSubtype() {
        return subtype;
    }

    public enum Subtype {
        ICE,
        WATER,
        STEAM
    }
}
