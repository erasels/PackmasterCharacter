package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.oraclepack.SelfFulfillmentPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SelfFulfillment extends AbstractOracleCard {

    public final static String ID = makeID("SelfFulfillment");
    // intellij stuff power, self, rare, , , , , 3, 1

    public SelfFulfillment() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new SelfFulfillmentPower(p));
    }

    public void upp() {
        isEthereal = false;
    }
}
