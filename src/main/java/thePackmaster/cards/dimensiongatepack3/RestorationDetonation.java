package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RestorationDetonation extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("RestorationDetonation");

    public RestorationDetonation() {
        super(ID, 2, CardRarity.RARE, CardType.SKILL, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 10;
        baseSecondMagic = secondMagic = 2;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {

        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;

                int healthgained = p.currentHealth;
                p.heal(magicNumber);
                healthgained = p.currentHealth - healthgained;

                if (healthgained > 0){
                    healthgained = healthgained * secondMagic;
                    Wiz.atb(new LoseHPAction(m, p, healthgained, AbstractGameAction.AttackEffect.FIRE));
                }

            }
        });

    }

    public void upp() {
        upgradeSecondMagic(1);
    }
}