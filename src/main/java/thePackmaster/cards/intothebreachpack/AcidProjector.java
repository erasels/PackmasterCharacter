package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.intothebreachpack.AcidPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class AcidProjector extends IntoTheBreachCard {
    public final static String ID = makeID("AcidProjector");

    public AcidProjector() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new AcidPower(m,1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
