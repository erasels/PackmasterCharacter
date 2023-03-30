package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.cosmoscommandpack.HeatEnginePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class HeatEngine extends AbstractCosmosCard {
    public final static String ID = makeID("HeatEngine");

    public HeatEngine() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new HeatEnginePower(p, magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}