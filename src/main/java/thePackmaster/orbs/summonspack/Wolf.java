package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMaxOrbAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.orbs.EmptyOrbSlot;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class Wolf extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Wolf.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/orbs/summonsPack/Wolf.png");
    private static final String IMG_PATH2 = makePath("/images/orbs/summonsPack/Wolf2.png");
    private static final Texture IMG2 = TexLoader.getTexture(IMG_PATH2);
    private static final float WOLF_WIDTH = 96.0f;

    private final static int BASE_PASSIVE = 1;
    private final static int ORB_BOOST = 1;
    private final static int BASE_EVOKE = 1;

    private final BobEffect wolfBobEffect = new BobEffect(2f, 3f);
    private float glintTimer;
    private Color colorNormal = Color.WHITE.cpy();
    private Color colorGlint = Color.WHITE.cpy();

    public Wolf()
    {
        super(ORB_ID, NAME, 0, BASE_EVOKE, "", "", IMG_PATH);
        glintTimer = MathUtils.random(5f, 15f);
        applyFocus();
        updateDescription();
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("EVENT_VAMP_BITE", 0.1F);
    }

    @Override
    public void applyFocus() {
        passiveAmount = BASE_PASSIVE;
        AbstractPower pow = adp().getPower(FocusPower.POWER_ID);
        if (pow != null)
            passiveAmount += pow.amount;
        for (AbstractOrb orb : adp().orbs)
            if (!(orb instanceof EmptyOrbSlot))
                passiveAmount += ORB_BOOST;
        if (passiveAmount < 0)
            passiveAmount = 0;
        evokeAmount = BASE_EVOKE;
    }

    @Override
    public void onEndOfTurn() {
        applyFocus();
        atb(new AbstractGameAction() {
            @Override
            public void update() {
                AbstractMonster m = Wiz.getLowestHealthEnemy();
                if (m == null) {
                    isDone = true;
                    return;
                }
                doDmg(m, Wolf.applyLockOn(m, passiveAmount), DamageInfo.DamageType.THORNS, AttackEffect.NONE, false, true);
                Wiz.att(new VFXAction(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F));
                isDone = true;
            }
        });
    }

    @Override
    public void onEvoke() {
        att(new IncreaseMaxOrbAction(evokeAmount));
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void updateAnimation() {
        wolfBobEffect.update();
        glintTimer -= Gdx.graphics.getDeltaTime();
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

        if (glintTimer <= 0)
            glintTimer = MathUtils.random(5f, 15f);

        if (glintTimer <= 1.5f && glintTimer >= 1f)
            colorGlint.set(1, 1, 1, (1.5f - glintTimer) * 2);
        else if (glintTimer < 1f && glintTimer > 0.5f)
            colorGlint.set(1, 1, 1, 1);
        else if (glintTimer <= 0.5f)
            colorGlint.set(1, 1, 1, (glintTimer) * 2);
        else
            colorGlint.set(1, 1, 1, 0);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setBlendFunction(770, 771);
        sb.setColor(colorNormal);
        sb.draw(img, cX - WOLF_WIDTH /2F, cY - WOLF_WIDTH /2F + wolfBobEffect.y, WOLF_WIDTH /2F, WOLF_WIDTH /2F,
                WOLF_WIDTH, WOLF_WIDTH, scale, scale, 0f, 0, 0, (int) WOLF_WIDTH, (int) WOLF_WIDTH,
                false, false);
        sb.setColor(colorGlint);
        sb.draw(IMG2, cX - WOLF_WIDTH /2F, cY - WOLF_WIDTH /2F + wolfBobEffect.y, WOLF_WIDTH /2F, WOLF_WIDTH /2F,
                WOLF_WIDTH, WOLF_WIDTH, scale, scale, 0f, 0, 0, (int) WOLF_WIDTH, (int) WOLF_WIDTH,
                false, false);
        sb.setColor(Color.WHITE.cpy());
        renderText(sb);
        hb.render(sb);
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Wolf();
    }
}