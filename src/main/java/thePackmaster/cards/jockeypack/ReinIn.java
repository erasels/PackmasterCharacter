package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.jockeypack.ReinInPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class ReinIn extends AbstractJockeyCard {
    public final static String ID = makeID("ReinIn");
    // intellij stuff skill, self, uncommon, , , 2, 1, , 

    public ReinIn() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(1));
        applyToSelf(new ReinInPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}