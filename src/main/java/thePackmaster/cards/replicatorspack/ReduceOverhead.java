package thePackmaster.cards.replicatorspack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.replicatorspack.ReduceOverheadAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ReduceOverhead extends AbstractReplicatorCard {


    public final static String ID = makeID("ReduceOverhead");

    public ReduceOverhead() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 7;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ReduceOverheadAction(new DamageInfo(p, this.damage, this.damageTypeForTurn), m));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}
