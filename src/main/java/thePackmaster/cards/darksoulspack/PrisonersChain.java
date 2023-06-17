package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.darksoulspack.PrisonersChainPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PrisonersChain extends AbstractDarkSoulsCard {
    public final static String ID = makeID("PrisonersChain");
    // intellij stuff power, self, uncommon, , , , , 3, 2

    public PrisonersChain() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new PrisonersChainPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}