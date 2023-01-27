package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardObelisk;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TomeOfIntellect extends AbstractDimensionalCardObelisk {
    public final static String ID = makeID("TomeOfIntellect");

    public TomeOfIntellect() {
        super(ID, 2, CardRarity.RARE, CardType.POWER, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        cardsToPreview = new ScrollOfIntellect();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MakeTempCardInDrawPileAction(new ScrollOfIntellect(), magicNumber, true, true));

    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}