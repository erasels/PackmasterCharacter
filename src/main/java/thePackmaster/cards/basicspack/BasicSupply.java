package thePackmaster.cards.basicspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.basicspack.BasicSupplyPower;
import thePackmaster.powers.basicspack.OckhamsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BasicSupply extends AbstractPackmasterCard {
    public final static String ID = makeID("BasicSupply");

    public BasicSupply() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, "basics");
        this.baseMagicNumber = this.magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new BasicSupplyPower(this.magicNumber)));
    }

    public void upp(){
        upgradeMagicNumber(1);
    }
}
