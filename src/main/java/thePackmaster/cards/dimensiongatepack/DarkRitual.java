package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class DarkRitual extends AbstractDimensionalCard {
    public final static String ID = makeID("DarkRitual");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public DarkRitual() {
        super(ID, 0, CardRarity.RARE, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.SELF);

        setFrame("darkritualframe.png");
        baseMagicNumber = magicNumber = 15;
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new LoseHPAction(p, p, magicNumber));
        atb(new GainGoldAction(100));
    }

    public void upp() {
        upgradeMagicNumber(-5);  // IF exhaustive charges, add one here?
    }
}