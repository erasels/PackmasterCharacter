package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.brickpack.JengaPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.applyToSelf;

public class Jenga extends AbstractBrickCard {
    public final static String ID = makeID(Jenga.class.getSimpleName());
    private static final int COST = 1;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 5;
    private static final int UPGRADE_MAGIC = 2;

    public Jenga() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new StrengthPower(adp(), magicNumber));
        applyToSelf(new JengaPower(1));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
