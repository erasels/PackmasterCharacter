package thePackmaster.cards.dimensiongatepack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.dimensiongatepack.ArsenalGearPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;


public class ArsenalGear extends AbstractDimensionalCard {
    public final static String ID = makeID("ArsenalGear");
    // intellij stuff attack, enemy, basic, 6, 3, , , ,

    public ArsenalGear() {
        super(ID, 2, CardRarity.UNCOMMON, CardType.POWER, AbstractCard.CardTarget.SELF);

        setFrame("arsenalgearframe.png");
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
       applyToSelf(new ArsenalGearPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}