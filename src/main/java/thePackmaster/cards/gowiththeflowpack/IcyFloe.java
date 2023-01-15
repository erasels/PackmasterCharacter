package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class IcyFloe extends AbstractHydrologistCard {
    public final static String ID = makeID("IcyFloe");

    public IcyFloe() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.ICE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}