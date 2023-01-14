package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.batterpack.OptimizedBlurPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OptimizedBlur extends AbstractPackmasterCard {
    public final static String ID = makeID("OptimizedBlur");

    public OptimizedBlur() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new OptimizedBlurPower(p, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
