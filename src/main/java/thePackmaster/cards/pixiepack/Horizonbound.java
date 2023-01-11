package thePackmaster.cards.pixiepack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.pixiepack.HorizonboundPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Horizonbound extends AbstractPixieCard {
    public final static String ID = makeID("Horizonbound");

    public Horizonbound() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.isEthereal = true;
    }

    @Override
    public void upp()
    {
        this.isEthereal=false;
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster)
    {
        addToBot(new ApplyPowerAction(abstractPlayer,abstractPlayer,new HorizonboundPower(abstractPlayer, 0)));
    }
}
