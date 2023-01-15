package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;
import thePackmaster.powers.alignmentpack.RememberPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Remember extends AbstractAstrologerCard {
    public final static String ID = makeID("Remember");

    public Remember() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RememberPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}