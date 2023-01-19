package thePackmaster.cards.replicatorspack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DuplicationPower;
import thePackmaster.actions.EasyXCostAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public class Xpansion extends AbstractReplicatorCard {


    public final static String ID = makeID("Xpansion");

    public Xpansion() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this,
                (effect, params)->{
                    if (effect > 0) {
                        att(new ApplyPowerAction(p, p, new DuplicationPower(p, effect)));
                    }
                    return true;
                }));
    }

    public void upp() {
        exhaust=false;
    }
}