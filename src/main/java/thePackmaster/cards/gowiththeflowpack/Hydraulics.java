package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Hydraulics extends AbstractHydrologistCard {
    public final static String ID = makeID("Hydraulics");

    public Hydraulics() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, Subtype.WATER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public void upp() {
    }
}