package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.entropypack.DiffusionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Diffusion extends AbstractPackmasterCard {
    public final static String ID = makeID("Diffusion");
    // intellij stuff power, none, uncommon, , , , , 2, 1

    public Diffusion() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DiffusionPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}