package thePackmaster.cards.fueledpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.basicspack.SimpleTrickPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class PowderyAsh extends AbstractFueledCard {
    public final static String ID = makeID(PowderyAsh.class.getSimpleName());
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final int COST = 1;

    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

    public PowderyAsh() {
        super(ID, COST, TYPE, RARITY, TARGET, CardColor.COLORLESS);
        baseMagicNumber = magicNumber = MAGIC;
        tags.add(SpireAnniversary5Mod.ASH);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new StrengthPower(adp(), magicNumber));
    }

    @Override
    public void upgrade() {
        upMagic(UPGRADE_MAGIC);
        upgraded = true;
        timesUpgraded++;
        name = cardStrings.NAME + "+" + timesUpgraded;
        initializeTitle();
    }

    @Override
    public void upp() {
        upgrade();
    }
}
