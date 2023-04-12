package thePackmaster.cards.darksoulspack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.darksoulspack.KarmicJusticePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class KarmicJustice extends AbstractDarkSoulsCard {

    //DEPRECATED - NOT PLANNED FOR USE

    public final static String ID = makeID("KarmicJustice");
    // intellij stuff power, self, rare, 20, , , , 5, 4

    public KarmicJustice() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new KarmicJusticePower(p, magicNumber));

    }

    public void upp() {
        upgradeMagicNumber(-1);
    }
}