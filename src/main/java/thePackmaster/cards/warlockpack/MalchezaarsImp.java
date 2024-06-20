package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.warlockpack.MalchezaarsImpPower;
import thePackmaster.powers.warlockpack.MalchezaarsImpUpgradedPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class MalchezaarsImp extends AbstractWarlockCard {
    public final static String ID = makeID(MalchezaarsImp.class.getSimpleName());

    private static final int COST = 3;

    public MalchezaarsImp() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new Imp();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!upgraded)
            applyToSelf(new MalchezaarsImpPower(p, 1));
        else
            applyToSelf(new MalchezaarsImpUpgradedPower(p, 1));
    }

    @Override
    public void upp() {
    }
}
