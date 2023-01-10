package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.summonspack.LouseEvokeAction;
import thePackmaster.patches.summonpack.PandaPatch;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class Louse extends CustomOrb implements OnPlayCardOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Louse.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/vfx/summonspack/Louse.png");
    private static final Texture IMG = TexLoader.getTexture(IMG_PATH);
    private static final float LOUSE_WIDTH = 96.0f;

    public static final float BOUNCE_DURATION = 1.0f;
    public static final float GRAVITY = 2700.0f;

    private boolean shooting = false;
    private Color color = Color.WHITE.cpy();
    private float spinTime = 0f;

    private final static int BASE_DAMAGE = 12;
    private final static int BASE_PASSIVE = 6;

    private float rotation;

    public boolean isCopy = false;

    public Louse()
    {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_DAMAGE, "", "", "");
        showEvokeValue = true;
        rotation = 0.0f;
        applyFocus();
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_DARK_CHANNEL", 0.1f);
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        evokeAmount += passiveAmount;
    }

    @Override
    public void applyFocus() {
        AbstractPower pow = adp().getPower(FocusPower.POWER_ID);
        if (pow == null) {
            passiveAmount = basePassiveAmount;
            return;
        }
        passiveAmount = basePassiveAmount + pow.amount;
    }

    @Override
    public void onEvoke() {
        Louse copy = (Louse) makeCopy();
        copy.isCopy = true;
        att(new LouseEvokeAction(copy, this));
    }

    public void startShoot() {
        shooting = true;
    }

    public void endShoot() {
        shooting = false;
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
        if (!shooting) {
            cX = MathHelper.orbLerpSnap(cX, tX);
            cY = MathHelper.orbLerpSnap(cY, tY);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        spinTime += Gdx.graphics.getDeltaTime();
        if (PandaPatch.AbstractOrbIsInPlayerRender.isPlayerRender.get(this))
            return;
        color.set((MathUtils.cosDeg(spinTime * 120f) + 1f)/2f,
                (MathUtils.cosDeg(spinTime * 120f + 120f) + 1f)/2f,
                (MathUtils.cosDeg(spinTime * 120f + 240f) + 1f)/2f,
                1f);
        sb.setColor(color);
        sb.setBlendFunction(770, 771);
        sb.draw(IMG, cX - LOUSE_WIDTH/2F, cY - LOUSE_WIDTH/2F, LOUSE_WIDTH/2F, LOUSE_WIDTH/2F,
                LOUSE_WIDTH, LOUSE_WIDTH, scale, scale, rotation, 0, 0, (int)LOUSE_WIDTH, (int)LOUSE_WIDTH,
                false, false);
        renderText(sb);
        hb.render(sb);
    }

    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, new Color(0.2F, 1.0F, 1.0F, this.c.a), this.fontScale);
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
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
        copy.evokeAmount = evokeAmount;
        copy.passiveAmount = passiveAmount;
        copy.spinTime = spinTime;
        return copy;
    }
}