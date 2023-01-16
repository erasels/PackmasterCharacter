package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
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
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.ThornsPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.summonspack.Quill;
import thePackmaster.orbs.PackmasterOrb;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.summonspack.QuillEffect;

import static thePackmaster.SpireAnniversary5Mod.PORCUPINE_KEY;
import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class Porcupine extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Porcupine.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/orbs/summonsPack/Porcupine.png");
    private static final float PORC_WIDTH = 96.0f;

    private final static int BASE_PASSIVE = 3;
    private final static int BASE_EVOKE = 1;
    private final static int NUM_PASSIVE = 1;

    private final BobEffect porcBobEffect = new BobEffect(2f, 3f);
    private float quillTimer;

    public Porcupine()
    {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH);
        quillTimer = MathUtils.random(2f, 10f);
        applyFocus();
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play(PORCUPINE_KEY, 0.1F);
    }

    @Override
    public void applyFocus() {
        passiveAmount = BASE_PASSIVE;
        evokeAmount = BASE_EVOKE;
        AbstractPower pow = adp().getPower(FocusPower.POWER_ID);
        if (pow != null) {
            passiveAmount = basePassiveAmount + pow.amount;
            evokeAmount = baseEvokeAmount + pow.amount;
        }
        if (passiveAmount < 0)
            passiveAmount = 0;
        if (evokeAmount < 0)
            evokeAmount = 0;
    }

    @Override
    public void onStartOfTurn() {
        Quill quill = new Quill();
        quill.baseDamage = passiveAmount;
        Wiz.makeInHand(quill, NUM_PASSIVE);
    }

    @Override
    public void onEvoke() {
        applyToSelf(new ThornsPower(adp(), evokeAmount));
    }

    @Override
    public void updateAnimation() {
        porcBobEffect.update();
        quillTimer -= Gdx.graphics.getDeltaTime();
        cX = MathHelper.orbLerpSnap(cX, AbstractDungeon.player.animX + tX);
        cY = MathHelper.orbLerpSnap(cY, AbstractDungeon.player.animY + tY);
        if (channelAnimTimer != 0.0F) {
            channelAnimTimer -= Gdx.graphics.getDeltaTime();
            if (channelAnimTimer < 0.0F) {
                channelAnimTimer = 0.0F;
            }
        }

        c.a = Interpolation.pow2In.apply(1.0F, 0.01F, channelAnimTimer / 0.5F);
        scale = Interpolation.swingIn.apply(Settings.scale, 0.01F, channelAnimTimer / 0.5F);

        if (quillTimer < 0f) {
            float x = MathUtils.random(-20f, 20f);
            float y = MathUtils.random(-20f, 20f);
            Wiz.vfx(new QuillEffect(hb.cX + x, hb.cY + y));
            quillTimer = MathUtils.random(2f, 10f);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 771);
        sb.setColor(Color.WHITE.cpy());
        sb.draw(img, cX - PORC_WIDTH /2F, cY - PORC_WIDTH /2F + porcBobEffect.y, PORC_WIDTH /2F, PORC_WIDTH /2F,
                PORC_WIDTH, PORC_WIDTH, scale, scale, 0f, 0, 0, (int) PORC_WIDTH, (int) PORC_WIDTH,
                false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + passiveAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Porcupine();
    }
}