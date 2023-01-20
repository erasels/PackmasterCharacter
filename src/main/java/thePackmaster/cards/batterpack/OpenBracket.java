package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.batterpack.OpenBracketPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class OpenBracket extends AbstractBatterCard {
    public final static String ID = makeID("OpenBracket");

    public OpenBracket() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = 2;
        magicNumber = this.baseMagicNumber;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DrawCardAction(this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new OpenBracketPower(p,-1),-1));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
