package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class StormFront extends AbstractHydrologistCard {
    public final static String ID = makeID("StormFront");

    public StormFront() {
        super(ID, 3, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, Subtype.STEAM);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}