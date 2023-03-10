package thePackmaster.cards.jockeypack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.arcanapack.HangedPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.makeInHand;

public class Homestretch extends AbstractPackmasterCard {
    public final static String ID = makeID("Homestretch");
    // intellij stuff skill, self, uncommon, , , , , 2, 1

    public Homestretch() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
        cardsToPreview = new Horse();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new Horse());
        applyToSelf(new HangedPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}