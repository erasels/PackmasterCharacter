package thePackmaster.cards.brickpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.TheBombPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class LeaningTower extends AbstractBrickCard implements StartupCard {
    public final static String ID = makeID(LeaningTower.class.getSimpleName());
    private static final int COST = -2;
    private static final CardType TYPE = CardType.SKILL;
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;

    private static final int MAGIC = 40;
    private static final int UPGRADE_DAMAGE = 10;
    private static final int SECOND_MAGIC = 5;

    public LeaningTower() {
        super(ID, COST, TYPE, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = SECOND_MAGIC;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        TheBombPower power = new TheBombPower(adp(), secondMagic, magicNumber);
        Wiz.applyToSelf(power);
        return true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_DAMAGE);
    }
}
