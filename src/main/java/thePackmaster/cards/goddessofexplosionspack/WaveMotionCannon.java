package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.goddessofexplosionspack.WaveMotionCannonPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WaveMotionCannon extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("WaveMotionCannon");

    private static final int BLOCK = 7;
    private static final int MAGIC = 7;
    private static final int BLOCK_UP = 2;
    private static final int MAGIC_UP = 2;

    public WaveMotionCannon() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, p, block));
        Wiz.applyToSelf(new WaveMotionCannonPower(p,magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(BLOCK_UP);
        upgradeMagicNumber(MAGIC_UP);
    }
}
