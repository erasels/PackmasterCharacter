package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.optionCards.LiveForever;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.witchesstrikepack.MysticFlourishAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.witchesstrikepack.MoonlightFlightPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MoonlightFlight extends AbstractPackmasterCard

    {
    public final static String ID = makeID("MoonlightFlight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public MoonlightFlight() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
         magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new MoonlightFlightPower(p,magicNumber)));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return LiveForever.ID;
    }
}
