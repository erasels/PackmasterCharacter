package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.intothebreachpack.VoidShockPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class VoidShocker extends IntoTheBreachCard {
    public final static String ID = makeID("VoidShocker");

    public VoidShocker() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 9;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new VoidShockPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}