package thePackmaster.cards.dimensiongatepack2;

import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardRoguebook;
import thePackmaster.powers.dimensiongatepack.DaggerstormPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;


public class Daggerstorm extends AbstractDimensionalCardRoguebook {
    public final static String ID = makeID("Daggerstorm");

    public Daggerstorm() {
        super(ID, 3, CardRarity.RARE, CardType.POWER, CardTarget.SELF);
        cardsToPreview = new Shiv();
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new DaggerstormPower(p, 1));
    }

    public void upp() {
        upgradeBaseCost(2);
    }
}