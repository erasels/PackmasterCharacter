package thePackmaster.cards.bellordpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.bellordpack.LikeClockworkPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class LikeClockwork extends AbstractBellordCard {
    public final static String ID = makeID("LikeClockwork");
    // intellij stuff power, self, rare, , , , , 5, 2

    public LikeClockwork() {
        super(ID, 0, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new LikeClockworkPower(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}