package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardInscryp;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RestorationDetonation extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("RestorationDetonation");

    public RestorationDetonation() {
        super(ID, 2, CardRarity.UNCOMMON, CardType.SKILL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 7;
        baseSecondMagic = secondMagic = 2;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }


    public void use(AbstractPlayer p, AbstractMonster m)
    {
        int healthgained = Math.min(Wiz.p().maxHealth - Wiz.p().currentHealth, magicNumber);

        healthgained = healthgained * secondMagic;
        Wiz.atb(new HealAction(p, p, magicNumber));
        if (healthgained > 0) Wiz.atb(new LoseHPAction(m, p, healthgained, AbstractGameAction.AttackEffect.FIRE));

    }

    public void upp() {
        upgradeSecondMagic(1);
    }
}