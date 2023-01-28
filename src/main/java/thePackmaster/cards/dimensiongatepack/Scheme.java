package thePackmaster.cards.dimensiongatepack;

import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.ExhaustiveField;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGrift;
import thePackmaster.powers.dimensiongate2pack.SchemeNextTurnPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Scheme extends AbstractDimensionalCardGrift {
    public final static String ID = makeID("Scheme");

    public Scheme() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.SELF);

        ExhaustiveField.ExhaustiveFields.baseExhaustive.set(this, 3);
        ExhaustiveField.ExhaustiveFields.exhaustive.set(this, 3);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SchemeNextTurnPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);

    }
}