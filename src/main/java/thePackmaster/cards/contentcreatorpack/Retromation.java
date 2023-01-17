package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.contentcreatorpack.RetromationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Retromation extends AbstractPackmasterCard {
    public final static String ID = makeID("Retromation");
    // intellij stuff power, self, uncommon, , , , , , 

    public Retromation() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new RetromationPower(1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}