package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardTrain;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PreservedThorns extends AbstractDimensionalCardTrain {
    public final static String ID = makeID("PreservedThorns");

    public PreservedThorns() {
        super(ID, 1, CardRarity.COMMON, CardType.SKILL,  CardTarget.SELF);
        baseBlock = 6;
        baseMagicNumber = 1;
        cardsToPreview = new Sting();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        for (int i = 0; i < magicNumber; i++) {

        }
        Wiz.atb(new MakeTempCardInHandAction(new Sting()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}