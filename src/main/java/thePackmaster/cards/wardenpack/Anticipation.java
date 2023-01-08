package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.wardenpack.AnticipationAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Anticipation extends AbstractPackmasterCard {
    public final static String ID = makeID("Anticipation");

    public Anticipation() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        this.baseMagicNumber = 1;
        this.magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(this.magicNumber, new AnticipationAction()));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
