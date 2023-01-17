package thePackmaster.cards.odditiespack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.odditiespack.AutoBattlerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class AutoBattler extends AbstractOdditiesCard {
    public final static String ID = makeID("AutoBattler");
    // intellij stuff power, self, rare, , , , , 3, 1

    public AutoBattler() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(p, magicNumber));
        applyToSelf(new DexterityPower(p, magicNumber));
        applyToSelf(new AutoBattlerPower());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}