package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cascade extends AbstractHydrologistCard {
    public final static String ID = makeID("Cascade");

    public Cascade() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, Subtype.WATER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}