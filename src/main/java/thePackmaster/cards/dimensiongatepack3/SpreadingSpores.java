package thePackmaster.cards.dimensiongatepack3;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class SpreadingSpores extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("SpreadingSpores");

    public SpreadingSpores() {
        super(ID, 1, CardRarity.UNCOMMON, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        isEthereal = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ThornsPower(p, magicNumber));
        Wiz.atb(new AddTemporaryHPAction(p, p, magicNumber));
        Wiz.atb(new MakeTempCardInDrawPileAction(this.makeStatEquivalentCopy(), 1, true, true));
    }

    public void upp() {
        isEthereal = false;
    }
}