package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class OffToTheRaces extends AbstractPackmasterCard {
    public final static String ID = makeID("OffToTheRaces");
    // intellij stuff power, self, rare, , , , , , 

    public OffToTheRaces() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        //TODO: Off to the Races power
    }

    public void upp() {
        selfRetain = true;
    }
}