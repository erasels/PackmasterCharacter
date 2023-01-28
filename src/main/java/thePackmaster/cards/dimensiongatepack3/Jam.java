package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardEden;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardObelisk;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Jam extends AbstractDimensionalCardEden {
    public final static String ID = makeID("Jam");

    public Jam() {
        super(ID, 0, CardRarity.SPECIAL, CardType.STATUS, CardTarget.SELF, CardColor.COLORLESS);
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelfDamageAction(new DamageInfo(p, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        Wiz.atb(new DrawCardAction(1));
    }

    public void upp() {

    }
}