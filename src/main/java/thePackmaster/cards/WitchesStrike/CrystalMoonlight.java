package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.orbs.AbstractPackMasterOrb;
import thePackmaster.orbs.WitchesStrike.CrescentMoon;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrystalMoonlight extends AbstractPackmasterCard {
    public final static String ID = makeID("CrystalMoonlight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrystalMoonlight() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            addToBot(new ManifestAction(AbstractPackMasterOrb.getPackLimitedOrb(true)));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
