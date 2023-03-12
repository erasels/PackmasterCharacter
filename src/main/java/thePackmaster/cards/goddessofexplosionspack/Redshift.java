package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.goddessofexplosionspack.RedshiftPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Redshift extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("Redshift");

    private static final int BLOCK = 12;
    private static final int MAGIC = 2;
    private static final int BLOCK_UP = 4;
    private static final int MAGIC_UP = 1;

    public Redshift() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = BLOCK;
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new GainBlockAction(p, p, block));
        Wiz.applyToSelf(new RedshiftPower(p,magicNumber));
    }

    @Override
    public void upp() {
        upgradeBlock(BLOCK_UP);
        upgradeMagicNumber(MAGIC_UP);
    }
}
