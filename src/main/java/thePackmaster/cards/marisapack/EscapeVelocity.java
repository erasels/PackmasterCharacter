package thePackmaster.cards.marisapack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EscapeVelocity extends AbstractMarisaCard {
    public final static String ID = makeID(EscapeVelocity.class.getSimpleName());
    private static final int BLK = 7, UPG_BLK = 4;

    public EscapeVelocity() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseBlock = block = BLK;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new ExplosionSmallEffect(p.hb.cX, p.hb.cY), Settings.ACTION_DUR_XFAST);
        blck();
    }

    @Override
    protected void applyPowersToBlock() {
        int tmpBlk = baseBlock;
        baseBlock += ChargeUpPower.chargeUpGainThisCombat;
        super.applyPowersToBlock();
        if(baseBlock != tmpBlk) isBlockModified = true;
        baseBlock = tmpBlk;
    }

    public void upp() {
        upgradeBlock(UPG_BLK);
    }
}
