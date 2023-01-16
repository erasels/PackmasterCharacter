package thePackmaster.cards.psychicpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.psychicpack.MakeCardInHandOccultAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class RuleCancel extends AbstractPackmasterCard {
    public final static String ID = makeID("RuleCancel");
    // intellij stuff skill, none, common, , , , , , 

    public RuleCancel() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new MakeCardInHandOccultAction(false));
    }

    public void upp() {
        exhaust = false;
    }
}