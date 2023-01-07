package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.intothebreachpack.EnrageShotAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class EnrageShot extends IntoTheBreachCard {
    public final static String ID = makeID("EnrageShot");

    public EnrageShot() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.ENEMY);
        magicNumber = baseMagicNumber = 2;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EnrageShotAction(this.magicNumber, m));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
