package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGordian;
import thePackmaster.powers.dimensiongatepack.FlamingGraspPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.MAGIC;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlamingGrasp extends AbstractDimensionalCardGordian {
    public final static String ID = makeID("FlamingGrasp");

    public FlamingGrasp() {
        super(ID, 1, CardRarity.COMMON, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 6;
        tags.add(MAGIC);
        cardsToPreview = new FlamePillar();

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        Wiz.applyToSelf(new FlamingGraspPower(p, 1));
    }

    public void upp() {
        upgradeDamage(3);
    }
}