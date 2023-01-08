package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.getEnemies;

public class Subspace extends AbstractPackmasterCard {
    public final static String ID = makeID("Subspace");

    public Subspace() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.ALL_ENEMY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : getEnemies()) {
            DistortionPower po = (DistortionPower) mo.getPower(DistortionPower.POWER_ID);
            if (po != null)
                po.enemyOnExhaust(this);
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}