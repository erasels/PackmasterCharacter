package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class UltimateShortwave extends AbstractMarisaCard implements AmplifyCard {
    public final static String ID = makeID(UltimateShortwave.class.getSimpleName());
    private static final int MAGIC = 1, S_MAGIC = 1, UPG_MAG = 2, INC = 1;

    public UltimateShortwave() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = MAGIC;
        baseSecondMagic = secondMagic = S_MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new ShockWaveEffect(p.hb.cX, p.hb.cY, Color.YELLOW.cpy(), ShockWaveEffect.ShockWaveType.NORMAL), Settings.ACTION_DUR_FASTER);
        Wiz.atb(new GainEnergyAction(magicNumber));
        Wiz.applyToSelf(new ChargeUpPower(secondMagic));
    }

    public void upp() {
        upgradeSecondMagic(UPG_MAG);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                UltimateShortwave.this.magicNumber = UltimateShortwave.this.baseMagicNumber += INC;
                UltimateShortwave.this.secondMagic = UltimateShortwave.this.baseSecondMagic += INC;
                isDone = true;
            }
        });
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        isSecondMagicModified = secondMagic != S_MAGIC;
        isMagicNumberModified = magicNumber != MAGIC;
    }

    @Override
    public int getAmplifyCost() {
        return 1;
    }
}
