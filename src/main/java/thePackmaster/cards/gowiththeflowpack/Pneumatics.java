package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Pneumatics extends AbstractHydrologistCard {
    public final static String ID = makeID("Pneumatics");

    public Pneumatics() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.STEAM);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}