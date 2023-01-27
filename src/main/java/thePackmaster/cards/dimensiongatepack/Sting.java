package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Sting extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("Sting");

    public Sting() {
        super(ID, 0, CardRarity.SPECIAL, CardType.ATTACK, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 2;
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToTop(new VFXAction(new ThrowDaggerEffect(m.hb.cX, m.hb.cY)));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        Wiz.atb(new ApplyPowerAction(p, p, new DrawCardNextTurnPower(p, 1), 1));

    }

    public void upp() {
        upgradeDamage(2);
    }
}