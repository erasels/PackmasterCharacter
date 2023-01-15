package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Aqueducts extends AbstractHydrologistCard {
    public final static String ID = makeID("Aqueducts");

    public Aqueducts() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.WATER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}