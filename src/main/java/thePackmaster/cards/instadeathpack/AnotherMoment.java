package thePackmaster.cards.instadeathpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.instadeathpack.AnotherMomentPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AnotherMoment extends AbstractInstadeathCard {
    public final static String ID = makeID("AnotherMoment");

    public AnotherMoment() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);

        isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AnotherMomentPower(p, 1), 1));
    }

    @Override
    public void upp() {
        isEthereal = false;
    }
}
