package thePackmaster.cards.brickpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.MetallicizePower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.brickpack.GolemPower;
import thePackmaster.powers.brickpack.JengaPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.applyToSelf;

public class BrickGolem extends AbstractBrickCard implements StartupCard {
    public final static String ID = makeID(BrickGolem.class.getSimpleName());
    private static final int COST = -2;
    private static final CardType TYPE = CardType.POWER;
    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 2;
    private static final int SECOND_MAGIC = 2;
    private static final int UPGRADE_SECOND = 1;

    public BrickGolem() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
        cardsToPreview = new HeavyBrick();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        applyToSelf(new MetallicizePower(adp(), magicNumber));
        applyToSelf(new ArtifactPower(adp(), secondMagic));
        applyToSelf(new GolemPower(1));
        return true;
    }

    @Override
    public void upp() {
        upMagic(UPGRADE_MAGIC);
        upgradeSecondMagic(UPGRADE_SECOND);
    }
}
