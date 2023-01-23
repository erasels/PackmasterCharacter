package thePackmaster.cards.WitchesStrike;

import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.witchesstrikepack.ManifestAction;
import thePackmaster.orbs.PackmasterOrb;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CrystalMoonlight extends AbstractWitchStrikeCard {
    public final static String ID = makeID("CrystalMoonlight");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public CrystalMoonlight() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++){
            addToBot(new ManifestAction(PackmasterOrb.getPackLimitedOrb(true)));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
    @Override
    public String cardArtCopy() {
        return Miracle.ID;
    }
}
