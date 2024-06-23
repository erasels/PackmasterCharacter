package thePackmaster.cards.marisapack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.DarkOrbActivateEffect;
import thePackmaster.powers.marisapack.ChargeUpPower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.BubbleEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GalacticHalo extends AbstractMarisaCard implements AmplifyCard {
    public final static String ID = makeID(GalacticHalo.class.getSimpleName());
    private static final int BLK = 12, BLK_UPG = 3;
    private static final int MAGIC = 3;

    public GalacticHalo() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = block = BLK;
        baseMagicNumber = magicNumber = MAGIC;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.vfx(new BubbleEffect(Color.SKY, ""), Settings.ACTION_DUR_FAST);
        Wiz.applyToSelf(new ChargeUpPower(magicNumber));
    }

    public void upp() {
       upgradeBlock(BLK_UPG);
    }

    @Override
    public boolean skipUseOnAmplify() {
        return false;
    }

    @Override
    public void useAmplified(AbstractPlayer p, AbstractMonster m) {
        queueVFXAroundPlayer();
        blck();
    }

    @Override
    public int getAmplifyCost() {
        return 2;
    }

    public void queueVFXAroundPlayer() {
        float playerX = Wiz.p().hb.cX;
        float playerY = Wiz.p().hb.cY;

        float distance = 20 * Settings.scale;

        // Angles in radians
        float[] angles = {0, (float)Math.PI / 2, (float)Math.PI, 3 * (float)Math.PI / 2};

        for (float angle : angles) {
            float vfxX = playerX + distance * (float)Math.cos(angle);
            float vfxY = playerY + distance * (float)Math.sin(angle);
            Wiz.vfx(new DarkOrbActivateEffect(vfxX, vfxY));
        }
    }
}
