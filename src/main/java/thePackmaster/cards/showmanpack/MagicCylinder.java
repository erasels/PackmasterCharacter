package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.showmanpack.MagicCylinderPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MagicCylinder extends AbstractShowmanCard {
    public final static String ID = makeID("MagicCylinder");

    public MagicCylinder() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MagicCylinderPower(p, 1)));
    }

    public void upp() {
        upgradeBaseCost(1);
    }
}