package thePackmaster.cards.ringofpainpack;

import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.ringofpainpack.HollowerAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Hollower extends AbstractPackmasterCard {
    public final static String ID = makeID(Hollower.class.getSimpleName());

    private static final int EXHAUST = 3;

    public Hollower() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        magicNumber = baseMagicNumber = EXHAUST;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new HollowerAction(magicNumber));
        atb(new BetterDiscardPileToHandAction(1));
    }

    public void upp() {
        isEthereal = false;
    }
}