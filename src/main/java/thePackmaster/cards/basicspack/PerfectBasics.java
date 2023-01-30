package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.Rummage;
import thePackmaster.powers.basicspack.PerfectBasicPower;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PerfectBasics extends AbstractPackmasterCard {
    public final static String ID = makeID("PerfectBasics");

    public PerfectBasics() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PerfectBasicPower(1)));
    }

    public void upp(){
        upgradeBaseCost(this.cost-1);
    }
}
