package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FinalForm extends AbstractOverwhelmingCard {
    public final static String ID = makeID("ActuallyFinalForm");

    public FinalForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

        this.magicNumber = this.baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}