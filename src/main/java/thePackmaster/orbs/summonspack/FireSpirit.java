package thePackmaster.orbs.summonspack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FlameBarrierPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleXLEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.AbstractPackMasterOrb;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.adp;

public class FireSpirit extends AbstractPackMasterOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(FireSpirit.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/vfx/summonspack/FireSpirit.png");
    private static final float SPIRIT_WIDTH = 96.0f;

    private final static int BASE_PASSIVE = 2;
    private final static int BASE_EVOKE = 4;

    private Color color = Color.WHITE.cpy();

    private float sparkTimer = 0.2f;

    private BobEffect fireBobEffect = new BobEffect(2f, 3f);

    public FireSpirit()
    {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH);
        applyFocus();
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("SOTE_SFX_FireIgnite_2_v1.ogg", 0.1f);
    }

    @Override
    public void passiveEffect() {
        return;
    }

    @Override
    public void applyFocus() {
        AbstractPower pow = adp().getPower(FocusPower.POWER_ID);
        if (pow == null) {
            passiveAmount = basePassiveAmount;
            evokeAmount = baseEvokeAmount;
            return;
        }
        passiveAmount = basePassiveAmount + pow.amount;
        evokeAmount = baseEvokeAmount + pow.amount;
    }

    @Override
    public void onEndOfTurn() {
        Wiz.applyToSelf(new FlameBarrierPower(adp(), passiveAmount));
    }

    @Override
    public void onEvoke() {
        Wiz.applyToSelfTop(new FlameBarrierPower(adp(), evokeAmount));
    }

    @Override
    public void updateAnimation() {
        fireBobEffect.update();

        sparkTimer -= Gdx.graphics.getDeltaTime();

        cX = MathHelper.orbLerpSnap(cX, adp().animX + tX);
        cY = MathHelper.orbLerpSnap(cY, adp().animY + tY);
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);

        if (sparkTimer <= 0) {
            AbstractDungeon.effectsQueue.add(
                    new TorchParticleXLEffect(cX + MathUtils.random(-30.0F, 30.0F) * Settings.scale,
                            cY + MathUtils.random(-25.0F, 25.0F) * Settings.scale));
            sparkTimer = MathUtils.random(0.05f, 0.5f);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - SPIRIT_WIDTH /2F, cY - SPIRIT_WIDTH /2F + fireBobEffect.y, SPIRIT_WIDTH /2F, SPIRIT_WIDTH /2F,
                SPIRIT_WIDTH, SPIRIT_WIDTH, scale, scale, 0f, 0, 0, (int) SPIRIT_WIDTH, (int) SPIRIT_WIDTH,
                false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + evokeAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new FireSpirit();
    }
}