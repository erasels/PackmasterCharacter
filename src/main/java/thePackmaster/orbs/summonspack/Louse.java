package thePackmaster.orbs.summonspack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.AbstractPackMasterOrb;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class Louse extends AbstractPackMasterOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Louse.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/orbs/summonsPack/Louse.png");
    private static final float LOUSE_WIDTH = 96.0f;

    private Color color = Color.WHITE.cpy();
    private float spinTime = 0f;

    private final static int PASSIVE_DRAW = 1;
    private final static int EVOKE_DRAW = 2;

    private float rotation;

    public Louse()
    {
        super(ORB_ID, NAME, PASSIVE_DRAW, EVOKE_DRAW, "", "", IMG_PATH);
        rotation = 0.0f;
        applyFocus();
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1f);
    }

    @Override
    public void applyFocus() {
        passiveAmount = PASSIVE_DRAW;
        evokeAmount = EVOKE_DRAW;
    }

    @Override
    public void onEvoke() {
        att(new DrawCardAction(EVOKE_DRAW));
    }

    @Override
    public void onStartOfTurn() {
        att(new DrawCardAction(PASSIVE_DRAW));
    }

    @Override
    public void updateAnimation() {
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }

        rotation += 0.5f*Gdx.graphics.getDeltaTime()*360.0f;

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);
        cX = MathHelper.orbLerpSnap(cX, tX);
        cY = MathHelper.orbLerpSnap(cY, tY);
    }

    @Override
    public void render(SpriteBatch sb) {
        spinTime += Gdx.graphics.getDeltaTime();
        color.set((MathUtils.cosDeg(spinTime * 120f) + 1.3f)/2.3f,
                (MathUtils.cosDeg(spinTime * 120f + 120f) + 1.3f)/2.3f,
                (MathUtils.cosDeg(spinTime * 120f + 240f) + 1.3f)/2.3f,
                1f);
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
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Louse();
    }
}