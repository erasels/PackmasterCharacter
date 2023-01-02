package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.actions.witchesstrikepack.MysticFlourishAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WitchTwist extends AbstractPackmasterCard {
    public final static String ID = makeID("WitchTwist");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public WitchTwist() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = 2;
        secondMagic =1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new MysticFlourishAction(magicNumber));
        addToBot(new ApplyPowerAction(p,p,new FocusPower(p,secondMagic)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondDamage(1);
    }
}
