package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.getEnemies;

public class Subspace extends AbstractCosmosCard {
    public final static String ID = makeID("Subspace");

    public Subspace() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        magicNumber = baseMagicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : getEnemies()) {
            DistortionPower po = (DistortionPower) mo.getPower(DistortionPower.POWER_ID);
            if (po != null) {
                if (upgraded)
                    for (int i = 0; i < magicNumber; i++)
                        po.enemyOnExhaust(this);
                else
                    po.enemyOnExhaust(this);
            }
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}