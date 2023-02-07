package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.entropypack.EntanglementPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Entanglement extends AbstractEntropyCard {
    public final static String ID = makeID("Entanglement");
    // intellij stuff power, none, rare, , , , , 5, 

    public Entanglement() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new EntanglementPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}