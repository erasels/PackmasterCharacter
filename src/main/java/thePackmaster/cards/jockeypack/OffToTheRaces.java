package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import thePackmaster.powers.jockeypack.OffToTheRacesPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class OffToTheRaces extends AbstractJockeyCard {
    public final static String ID = makeID("OffToTheRaces");
    // intellij stuff power, self, rare, , , , , , 

    public OffToTheRaces() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DexterityPower(p, 2));
        applyToSelf(new OffToTheRacesPower(p, 1));
    }

    public void upp() {
        selfRetain = true;
    }
}