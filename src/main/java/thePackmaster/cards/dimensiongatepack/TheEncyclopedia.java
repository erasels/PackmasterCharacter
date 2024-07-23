package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.dimensiongatepack.EncyclopediaAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardObelisk;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TheEncyclopedia extends AbstractDimensionalCardObelisk {
    public final static String ID = makeID("TheEncyclopedia");

    public TheEncyclopedia() {
        super(ID, 1, CardRarity.RARE, CardType.SKILL, CardTarget.SELF);
        exhaust = true;
        baseMagicNumber = magicNumber = 6;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new EncyclopediaAction(magicNumber, cardStrings.EXTENDED_DESCRIPTION[0]));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }

}