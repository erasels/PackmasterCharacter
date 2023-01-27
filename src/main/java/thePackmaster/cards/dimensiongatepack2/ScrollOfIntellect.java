package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.PutOnDeckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardObelisk;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ScrollOfIntellect extends AbstractDimensionalCardObelisk {
    public final static String ID = makeID("ScrollOfIntellect");

    public ScrollOfIntellect() {
        super(ID, 0, CardRarity.SPECIAL, CardType.SKILL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(magicNumber));
        Wiz.atb(new GainEnergyAction(1));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}