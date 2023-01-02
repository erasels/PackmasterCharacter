package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonspack.LouseEvokeAction;
import thePackmaster.actions.summonspack.LouseSmackAction;
import thePackmaster.patches.summonpack.PandaPatch;

import static java.lang.Math.pow;
import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class Louse extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Louse.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/vfx/summonspack/Louse.png");
    private static final float LOUSE_WIDTH = 96.0f;
    private static final Color TEXT_COLOR = new Color(1.0f, 0.25f, 0.25f, 1.0f);

    public static final float BOUNCE_DURATION = 1.0f;
    public static final float GRAVITY = 2700.0f;

    private float bounceTime = 0;
    private boolean shooting = false;
    private boolean bouncing = false;
    private float bounceSourceX;
    private float bounceSourceY;
    private float peakY;
    private float peakTime;
    private Color color = Color.WHITE.cpy();
    private final float timerOffset;
    private float spinTime = 0f;

    private static int BASE_DAMAGE = 5;

    private float rotation;

    public boolean isCopy = false;

    public Louse()
    {
        super(ORB_ID, NAME, BASE_DAMAGE, BASE_DAMAGE, "", "",IMG_PATH);
        showEvokeValue = false;
        rotation = 0.0f;
        applyFocus();
        updateDescription();
        timerOffset = MathUtils.random(0f, 360f);
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1f);
    }

    @Override
    public void onEvoke() {
        Louse copy = (Louse) makeCopy();
        copy.isCopy = true;
        att(new LouseEvokeAction(copy, this));
    }

    @Override
    public void onEndOfTurn() {
        atb(new LouseSmackAction(this));
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
        spinTime += Gdx.graphics.getDeltaTime();
        if (PandaPatch.AbstractOrbIsInPlayerRender.isPlayerRender.get(this))
            return;
        color.set((MathUtils.cosDeg((float) ((spinTime + timerOffset) / 10L % 360L)) + 1.25F) / 2.3F,
                (MathUtils.cosDeg((float) ((spinTime + 1000L + timerOffset) / 10L % 360L)) + 1.25F) / 2.3F,
                (MathUtils.cosDeg((float) ((spinTime + 2000L + timerOffset) / 10L % 360L)) + 1.25F) / 2.3F,
                0f);
        sb.setColor(color);
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - LOUSE_WIDTH/2F, cY - LOUSE_WIDTH/2F, LOUSE_WIDTH/2F, LOUSE_WIDTH/2F,
                LOUSE_WIDTH, LOUSE_WIDTH, scale, scale, rotation, 0, 0, (int)LOUSE_WIDTH, (int)LOUSE_WIDTH,
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
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + passiveAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy() {
        Louse copy = new Louse();
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