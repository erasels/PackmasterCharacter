package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTowerTactics;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SpreadingSpores extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("SpreadingSpores");

    public SpreadingSpores() {
        super(ID, 1, CardRarity.RARE, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        tags.add(CardTags.HEALING);
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ThornsPower(p, 5));
        Wiz.applyToSelf(new RegenPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}