package thePackmaster.cards.brickpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.brickpack.SalvagePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Salvage extends AbstractBrickCard {
    public final static String ID = makeID(Salvage.class.getSimpleName());
    private static final int COST = 2;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 6;
    private static final int UPGRADE_MAGIC = 2;

    public Salvage() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        isInnate = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SalvagePower(magicNumber));
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
    }
}
