package thePackmaster.cards.bellordpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.bellordpack.CuckooPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Cuckoo extends AbstractPackmasterCard {
    public final static String ID = makeID("Cuckoo");
    // intellij stuff power, self, uncommon, , , , , 13, 

    public Cuckoo() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 13;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CuckooPower(magicNumber));
    }

    public void upp() {
        isInnate = true;
    }
}