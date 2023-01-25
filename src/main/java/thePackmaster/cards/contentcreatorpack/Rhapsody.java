package thePackmaster.cards.contentcreatorpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.bardinspirepack.InspirationPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;
import static thePackmaster.util.Wiz.atb;

public class Rhapsody extends AbstractContentCard {
    public final static String ID = makeID("Rhapsody");
    public Rhapsody() {
        super(ID, 2, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 125;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new InspirationPower(p, 1, magicNumber));
        atb(new DrawCardAction(this.secondMagic));
    }

    public void upp() {
        upgradeMagicNumber(25);
        upgradeSecondMagic(1);
    }
}