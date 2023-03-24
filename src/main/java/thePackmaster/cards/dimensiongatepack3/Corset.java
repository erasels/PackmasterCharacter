package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardEden;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Corset extends AbstractDimensionalCardEden {
    public final static String ID = makeID("Corset");

    public Corset() {
        super(ID, 1, CardRarity.COMMON, CardType.SKILL, CardTarget.SELF);
        baseBlock = 10;
        cardsToPreview = new Jam();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.atb(new MakeTempCardInHandAction(new Jam()));
    }

    public void upp() {
        upgradeBlock(3);

    }
}