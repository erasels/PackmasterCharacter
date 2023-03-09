package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.oraclepack.ButterflyEffectPower;
import thePackmaster.powers.oraclepack.SelfFulfillmentPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ButterflyEffect extends AbstractOracleCard {

    public final static String ID = makeID("ButterflyEffect");
    // intellij stuff power, self, rare, , , , , 3, 1

    public ButterflyEffect() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ButterflyEffectPower(p, 1));
    }

    public void upp() {
        exhaust = false;
    }
}
