/*package thePackmaster.cards.demopack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PreppingUp extends AbstractPackmasterCard {
    public final static String ID = makeID("PreppingUp");
    // intellij stuff skill, self, common, , , 7, 1, 2, 1

    public PreppingUp() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new DrawCardNextTurnPower(p, magicNumber));
    }

    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }
}

 */