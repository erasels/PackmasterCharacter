package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Recurrence extends AbstractWardenCard {
    public final static String ID = makeID("Recurrence");

    public Recurrence() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractCard c : Wiz.p().hand.group)
        {
            if (c != this)
            this.addToBot(new MakeTempCardInDrawPileAction(c.makeStatEquivalentCopy(), 1, true, true));
        }
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}
