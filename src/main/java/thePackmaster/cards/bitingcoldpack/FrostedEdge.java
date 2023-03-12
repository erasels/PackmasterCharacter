package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bitingcoldpack.FrostedEdgePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class FrostedEdge extends BitingColdCard {
    // TODO: add art lmao
    public final static String ID = makeID("FrostedEdge");

    public FrostedEdge() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new FrostedEdgePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}