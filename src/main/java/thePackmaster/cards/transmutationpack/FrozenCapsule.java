package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class FrozenCapsule extends AbstractHydrologistCard {
    public final static String ID = makeID("FrozenCapsule");
    // intellij stuff attack, enemy, rare, 8, , , , 1, 1

    public FrozenCapsule() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY, Subtype.ICE);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}