package thePackmaster.cards.psychicpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.actions.psychicpack.SuperFastDamageAction;
import thePackmaster.patches.psychicpack.occult.OccultFields;
import thePackmaster.vfx.psychicpack.EradicationEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class MoldedFire extends AbstractPsychicCard {
    public final static String ID = makeID("MoldedFire");
    // intellij stuff attack, enemy, special, 11, 4, , , , 

    public MoldedFire() {
        this(-1);
    }

    public MoldedFire(int cost) {
        super(ID, cost, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 11;
        OccultFields.isOccult.set(this, true);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int gap = this.costForTurn - EnergyPanel.totalCount; //energy is spent after the use method is called.
        if (gap > 0) {
            atb(new VFXAction(new EradicationEffect(m.hb.cX, m.hb.cY, gap * 2), Settings.FAST_MODE ? 0.3f : 0.9f));
            for (int i = 0; i < gap; ++i)
            {
                atb(new SuperFastDamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
            }
        }
    }

    public void upp() {
        upgradeDamage(4);
    }

    @Override
    public AbstractCard makeCopy() {
        return new MoldedFire(cost);
    }
}