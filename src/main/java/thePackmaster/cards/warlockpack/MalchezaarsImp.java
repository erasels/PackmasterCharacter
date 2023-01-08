package thePackmaster.cards.warlockpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.warlockpack.MalchezaarsImpPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToSelf;

public class MalchezaarsImp extends AbstractPackmasterCard {
    public final static String ID = makeID(MalchezaarsImp.class.getSimpleName());

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public MalchezaarsImp() {
        super(ID, COST, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        this.cardsToPreview = new Imp();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new MalchezaarsImpPower(p, 1));
    }

    @Override
    public void upp() {
        upgradeBaseCost(UPGRADE_COST);
    }
}
