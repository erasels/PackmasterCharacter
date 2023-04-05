package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.cosmoscommandpack.ArcingEnergyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class ArcingEnergy extends AbstractCosmosCard {
    public final static String ID = makeID("ArcingEnergy");

    public ArcingEnergy() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new ArcingEnergyPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}