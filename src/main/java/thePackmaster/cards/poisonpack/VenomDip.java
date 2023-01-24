package thePackmaster.cards.poisonpack;


import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnvenomPower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.poisonpack.ToxicDipPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class VenomDip extends AbstractPackmasterCard {
    public final static String ID = makeID("VenomDip");

    public VenomDip() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new ToxicDipPower(p, magicNumber));
    }


    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}


