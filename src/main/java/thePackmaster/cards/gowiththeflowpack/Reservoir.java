package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Reservoir extends AbstractHydrologistCard {
    public final static String ID = makeID("Reservoir");

    public Reservoir() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, Subtype.WATER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}