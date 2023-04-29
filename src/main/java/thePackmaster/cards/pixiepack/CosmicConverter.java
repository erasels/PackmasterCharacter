package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pixiepack.CosmicConverterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CosmicConverter extends AbstractPixieCard {
    public final static String ID = makeID("CosmicConverter");

    public CosmicConverter() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
    }

    @Override
    public void upp()
    {
       this.upgradeBaseCost(1);
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new CosmicConverterPower(abstractPlayer, 1)));
    }
}
