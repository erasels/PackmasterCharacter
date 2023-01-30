package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.basicspack.OckhamsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OckhamsRazor extends AbstractPackmasterCard {
    public final static String ID = makeID("OckhamsRazor");

    public OckhamsRazor() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF, "basics");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new OckhamsPower(1)));
    }

    public void upp(){
        upgradeBaseCost(this.cost-1);
    }
}
