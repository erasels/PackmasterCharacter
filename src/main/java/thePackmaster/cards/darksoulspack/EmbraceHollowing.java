package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.darksoulspack.EmbraceHollowingPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class EmbraceHollowing extends AbstractDarkSoulsCard {
    public final static String ID = makeID("EmbraceHollowing");
    // intellij stuff power, self, rare, , , , , 5, 3

    public EmbraceHollowing() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new EmbraceHollowingPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}