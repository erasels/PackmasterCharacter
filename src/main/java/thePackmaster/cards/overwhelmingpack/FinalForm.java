package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.overwhelmingpack.ActuallyFinalFormPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FinalForm extends AbstractOverwhelmingCard {
    public final static String ID = makeID("ActuallyFinalForm");

    public FinalForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

        this.isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ActuallyFinalFormPower(p, 1), 1));
    }

    public void upp() {
        this.isEthereal = false;
    }
}