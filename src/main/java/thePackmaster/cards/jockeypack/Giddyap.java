package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Giddyap extends AbstractPackmasterCard {
    public final static String ID = makeID("Giddyap");
    // intellij stuff power, self, rare, , , , , , 

    public Giddyap() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO: Giddyap Power
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}