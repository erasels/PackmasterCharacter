package thePackmaster.cards.frostpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Snack extends AbstractFrostCard {
    public final static String ID = makeID("Snack");

    public Snack() {
        super(ID, 3, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(p, 2));
        Wiz.applyToSelf(new DexterityPower(p, 2));
        Wiz.applyToSelf(new FocusPower(p, 2));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}