package thePackmaster.cards.bitingcoldpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.bitingcoldpack.FrigidBodyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class FrigidBody extends BitingColdCard {
    public final static String ID = makeID("FrigidBody");

    public FrigidBody() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        block = baseBlock = 8;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new FrigidBodyPower(p));
    }

    public void upp() {
        upgradeBlock(3);
    }
}