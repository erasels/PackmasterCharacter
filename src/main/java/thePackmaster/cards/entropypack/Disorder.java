package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.entropypack.EntropyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Disorder extends AbstractEntropyCard {
    public final static String ID = makeID("Disorder");
    // intellij stuff skill, self, common, , , 8, 3, 2, 1

    public Disorder() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 9;
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new EntropyPower(p, this.magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}