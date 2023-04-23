package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardGordian;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Channel extends AbstractDimensionalCardGordian {
    public final static String ID = makeID("Channel");

    public Channel() {
        super(ID, 0, CardRarity.SPECIAL, CardType.SKILL, CardTarget.SELF, CardColor.COLORLESS);
        exhaust = true;
        baseMagicNumber = magicNumber = 1;
        
        selfRetain = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> targets = new ArrayList<>();
        for (AbstractCard c : p.hand.group
        ) {
            if (c.cost >= 0 && c.costForTurn > 0) {
                targets.add(c);
            }
        }
        if (!targets.isEmpty()) {
            addToBot(new ReduceCostForTurnAction(Wiz.getRandomItem(targets), magicNumber));
        }

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}