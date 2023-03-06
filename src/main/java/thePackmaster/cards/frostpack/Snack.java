package thePackmaster.cards.frostpack;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
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
        super(ID, 4, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF);
        baseMagicNumber = magicNumber = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new HealAction(p, p, magicNumber));
        Wiz.applyToSelf(new FocusPower(p, 1));
        Wiz.atb(new IncreaseMaxOrbAction(1));
        Wiz.applyToSelf(new StrengthPower(p, 1));
        Wiz.applyToSelf(new DexterityPower(p, 1));
    }

    public void upp() {
        upgradeMagicNumber(4);
    }
}