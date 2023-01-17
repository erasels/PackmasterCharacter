package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.ringofpainpack.HollowerAction;
import thePackmaster.actions.ringofpainpack.HollowerDiscardPileToHandAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Hollower extends AbstractRingOfPainCard {
    public final static String ID = makeID(Hollower.class.getSimpleName());

    private static final int EXHAUST = 2;

    public Hollower() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = EXHAUST;
        isEthereal = true;

        
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HollowerAction(magicNumber));
        atb(new HollowerDiscardPileToHandAction(1));
    }

    public void upp() {
        isEthereal = false;
    }
}