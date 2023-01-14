package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonspack.PandaEvokeAction;
import thePackmaster.actions.summonspack.PandaSmackAction;
import thePackmaster.patches.summonpack.PandaPatch;

import static java.lang.Math.pow;
import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class Panda extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Panda.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/vfx/summonspack/Panda.png");
    private static final float PANDA_WIDTH = 96.0f;
    private static final Color TEXT_COLOR = new Color(1.0f, 0.25f, 0.25f, 1.0f);
    private static final int BASE_PASSIVE = 8;
    private static final int BASE_EVOKE = 12;

    // DO NOT SET EITHER OF THESE TO ZERO
    public static final float BOUNCE_DURATION = 1.0f;
    public static final float GRAVITY = 2700.0f*Settings.scale;

    private float bounceTime = 0;
    private boolean shooting = false;
    private boolean bouncing = false;
    private float bounceSourceX;
    private float bounceSourceY;
    private float peakY;
    private float peakTime;

    private float rotation;

    public boolean isCopy = false;

    public Panda()
    {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH);
        showEvokeValue = false;
        rotation = 0.0f;
        applyFocus();
        updateDescription();
    }

    public void applyFocus() {
        AbstractPower power = adp().getPower(FocusPower.POWER_ID);
        if (power != null) {
            passiveAmount = Math.max(0, basePassiveAmount + power.amount);
            evokeAmount = Math.max(0, baseEvokeAmount + power.amount);
            return;
        }

        passiveAmount = basePassiveAmount;
        evokeAmount = baseEvokeAmount;
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1f);
    }

    @Override
    public void onEvoke() {
        Panda copy = (Panda) makeCopy();
        copy.isCopy = true;
        att(new PandaEvokeAction(copy, this));
    }

    @Override
    public void onEndOfTurn() {
        atb(new PandaSmackAction(this));
    }

    public void startShoot() {
        shooting = true;
        bouncing = false;
    }

    public void endShoot() {
        shooting = false;
        bouncing = false;
    }

    public void startBounce(float sourceX, float sourceY) {
        bounceTime = 0;
        bouncing = true;
        bounceSourceX = sourceX;
        bounceSourceY = sourceY;
        calculatePeak();
    }

    private void calculatePeak() {
        peakTime = (tY - bounceSourceY) / BOUNCE_DURATION / GRAVITY + BOUNCE_DURATION / 2;
        peakY = GRAVITY * peakTime * peakTime / 2.0f + bounceSourceY;
    }

    @Override
    public void updateAnimation() {
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }
        if (shooting)
            rotation += 6*Gdx.graphics.getDeltaTime()*360.0f;
        else
            rotation += 0.5f*Gdx.graphics.getDeltaTime()*360.0f;

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);
        if (shooting) {
            if (bouncing) {
                bounceTime += Gdx.graphics.getDeltaTime();
                if (bounceTime > BOUNCE_DURATION) {
                    cX = tX;
                    cY = tY;
                    endShoot();
                } else {
                    cX = bounceSourceX + (tX - bounceSourceX) * (bounceTime / BOUNCE_DURATION);
                    cY = (float)(peakY - pow(bounceTime - peakTime, 2)*GRAVITY/2.0f);
                }
            }
        }
        else {
            cX = MathHelper.orbLerpSnap(cX, tX);
            cY = MathHelper.orbLerpSnap(cY, tY);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (PandaPatch.AbstractOrbIsInPlayerRender.isPlayerRender.get(this))
            return;
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - PANDA_WIDTH/2F, cY - PANDA_WIDTH/2F, PANDA_WIDTH/2F, PANDA_WIDTH/2F,
                PANDA_WIDTH, PANDA_WIDTH, scale, scale, rotation, 0, 0, (int)PANDA_WIDTH, (int)PANDA_WIDTH,
                false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        if (!shooting)
            FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount),
                    cX + NUM_X_OFFSET + 25*Settings.scale, cY + NUM_Y_OFFSET - 25* Settings.yScale,
                    TEXT_COLOR, fontScale);
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + evokeAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy() {
        Panda copy = new Panda();
        copy.cX = cX;
        copy.cY = cY;
        copy.tX = tX;
        copy.tY = tY;
        copy.hb.move(tX, tY);
        copy.rotation = rotation;
        copy.c.a = 1;
        copy.scale = Settings.scale;
        channelAnimTimer = 0;
        return copy;
    }
}

/*
    ym - ys = xm*xm*a/2
    ym - yt = (dur - xm)^2 *a/2
    ym = xm*xm*a/2 + ys
    xm*xm*a/2 + ys - yt = (dur - xm)^2 *a/2 = [dur^2 -2dur*xm + xm^2]*a/2
    ys - yt = [dur^2 - 2dur*xm]*a/2
    ys - yt - dur^2 *a/2 = -dur*xm * a
    [ys - yt - dur^2 * a/2] / -dur*a = xm
    xm = (yt - ys)/dur/a + dur/2
 */