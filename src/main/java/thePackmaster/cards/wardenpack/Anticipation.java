package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.wardenpack.AnticipationAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Anticipation extends AbstractWardenCard {
    public final static String ID = makeID("Anticipation");

    public Anticipation() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = 2;
        magicNumber = baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DrawCardAction(this.magicNumber, new AnticipationAction(upgraded)));
    }
}
