package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.ringofpainpack.HollowerAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Hollower extends AbstractRingOfPainCard {
    public final static String ID = makeID(Hollower.class.getSimpleName());

    public Hollower() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HollowerAction(1, !upgraded));
    }

    public void upp() {
        selfRetain = true;
    }
}