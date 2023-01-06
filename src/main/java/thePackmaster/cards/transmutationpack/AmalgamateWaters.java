package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class AmalgamateWaters extends AbstractHydrologistCard {
    public final static String ID = makeID("AmalgamateWaters");
    // intellij stuff , none, rare, , , , , , 

    public AmalgamateWaters() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, Subtype.WATER);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeBaseCost(1);
    }
}