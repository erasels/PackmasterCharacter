package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.farmerpack.SupplyChainPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class SupplyChain extends AbstractFarmerCard {
    public final static String ID = makeID("SupplyChain");

    public SupplyChain() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        cardsToPreview = new Fertilizer();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p,new SupplyChainPower(p, magicNumber)));
    }

    public void upp() {
        this.isInnate = true;
    }
}
