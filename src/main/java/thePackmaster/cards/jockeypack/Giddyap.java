package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.jockeypack.GiddyapPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Giddyap extends AbstractJockeyCard {
    public final static String ID = makeID("Giddyap");
    // intellij stuff power, self, rare, , , , , , 

    public Giddyap() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        cardsToPreview = new Horse();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GiddyapPower());
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}