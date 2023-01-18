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
        super(cardID, cost, type, rarity, target, "hydrologist", "hydrologist/orb.png");

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
