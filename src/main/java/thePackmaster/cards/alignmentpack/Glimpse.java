package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;
import thePackmaster.powers.alignmentpack.GlimpsePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class Glimpse extends AbstractAstrologerCard {
    public final static String ID = makeID("Glimpse");

    public Glimpse() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new GlimpsePower(p, this.magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}