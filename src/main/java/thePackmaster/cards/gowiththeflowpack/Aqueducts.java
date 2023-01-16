package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;
import thePackmaster.powers.gowiththeflowpack.AqueductsPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Aqueducts extends AbstractHydrologistCard {
    public final static String ID = makeID("Aqueducts");

    public Aqueducts() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.NONE, Subtype.WATER);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AqueductsPower(p, magicNumber), magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}