package thePackmaster.cards.upgradespack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.upgradespack.SharpeningFollowUpAction;
import thePackmaster.cards.AbstractPackmasterCard;

public class SharpeningSheath extends AbstractBlacksmithCard {

    public final static String ID = SpireAnniversary5Mod.makeID("SharpeningSheath");

    public SharpeningSheath() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(magicNumber,new SharpeningFollowUpAction()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
