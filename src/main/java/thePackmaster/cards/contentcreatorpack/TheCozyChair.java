package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.contentcreatorpack.CozyChairPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class TheCozyChair extends AbstractContentCard {
    public final static String ID = makeID("TheCozyChair");

    public TheCozyChair() {
        super(ID, 3, CardType.POWER, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CozyChairPower(1));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}