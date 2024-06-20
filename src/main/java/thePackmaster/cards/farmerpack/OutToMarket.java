package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.farmerpack.OutToMarketPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class OutToMarket extends AbstractFarmerCard {
    public final static String ID = makeID("OutToMarket");

    public OutToMarket() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p,new OutToMarketPower(p, magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
