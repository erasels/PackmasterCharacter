package thePackmaster.cards.intriguepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.intriguepack.PowerStrugglePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PowerStruggle extends AbstractIntrigueCard {
    public final static String ID = makeID("PowerStruggle");

    public PowerStruggle() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new PowerStrugglePower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}
