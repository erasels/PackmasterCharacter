package thePackmaster.cards.farmerpack;

import static thePackmaster.util.Wiz.*;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Fertilizer extends AbstractFarmerCard {
    public final static String ID = makeID("Fertilizer");

    public Fertilizer() {
        super(ID, 0, CardType.STATUS, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = 1;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new DrawCardAction(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
