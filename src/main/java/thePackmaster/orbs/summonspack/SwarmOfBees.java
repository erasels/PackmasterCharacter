package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

import static com.badlogic.gdx.math.MathUtils.*;
import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class SwarmOfBees extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(SwarmOfBees.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH_O = makePath("images/orbs/summonsPack/Empty.png");
    private static final String IMG_PATH = makePath("images/orbs/summonsPack/Bee.png");
    private static final Texture BEE_IMG = ImageMaster.loadImage(IMG_PATH);
    private static final int BEE_COUNT = 120;
    public static final int BASE_PASSIVE = 2;
    public static final int BASE_EVOKE = 6;
    private static final Color STING_COLOR = Color.YELLOW.cpy();

    private static final float SPAWN_DISTANCE = 40f*Settings.scale;
    private static final float TETHER_DISTANCE = SPAWN_DISTANCE;
    private static final float SPAWN_VELOCITY_VAR = 40f*Settings.scale;
    private static final float ACC_VAR = 40f*Settings.scale;

    private final ArrayList<Bee> bees = new ArrayList<>();

    private class Bee {

        private float x, y;
        private float vMag, vAngle;
        private float aTurn, aVel;
        private float timer;
        private float rotation;

        private Bee() {
            float dist = MathUtils.randomTriangular(0f, SPAWN_DISTANCE);
            float distAngle = MathUtils.random(0f, MathUtils.PI2);
            x = dist * cos(distAngle);
            y = dist * sin(distAngle);

            vMag = MathUtils.random(SPAWN_VELOCITY_VAR*0.2f, SPAWN_VELOCITY_VAR);
            vAngle = MathUtils.random(0f, MathUtils.PI2);

            rotation = vAngle*180f/MathUtils.PI;

            aVel = MathUtils.random(0f, ACC_VAR);
            aTurn = MathUtils.random(0, 4f*PI);
            timer = MathUtils.random(0.35f, 0.65f);

            hb.height = 128f;
            hb.width = 128f;
        }

        private void generateRandomAcc() {
            float dist = (float)Math.sqrt(x*x + y*y);
            float distAngle = atan2(-y, -x);

            while (vAngle > PI)
                vAngle -= PI2;
            while (vAngle < -PI)
                vAngle += PI2;

            aVel = (SPAWN_VELOCITY_VAR/2f - vMag) + SPAWN_VELOCITY_VAR*MathUtils.random(-0.25f, 0.25f);

            if (dist > TETHER_DISTANCE) {
                float deltaAng = vAngle - distAngle;
                while (deltaAng > PI)
                    deltaAng -= PI2;
                while (deltaAng < -PI)
                    deltaAng += PI2;

                aTurn = -deltaAng/(MathUtils.random(0.45f, 0.525f));

                timer = 0.5f;
            } else {
                aTurn = MathUtils.random(-0.5f, 0.5f) * PI;
                timer = MathUtils.random(2.0f, 4.0f);
            }
        }

        private void update() {
            float delta = Gdx.graphics.getDeltaTime();

            x += (vMag * cos(vAngle))*delta;
            y += (vMag * sin(vAngle))*delta;

            // this one needs to be in degrees
            rotation = vAngle*180f/MathUtils.PI;
            hb.move(x + cX, y + cY);

            vMag += aVel*delta;

            if (vMag < SPAWN_VELOCITY_VAR*0.1f)
                vMag = SPAWN_VELOCITY_VAR*0.1f;

            if (vMag > SPAWN_VELOCITY_VAR*1.5f)
                vMag = SPAWN_VELOCITY_VAR*1.5f;

            vAngle += aTurn*delta;

            timer -= delta;
            if (timer < 0)
                generateRandomAcc();
        }

        private void render(SpriteBatch sb) {
            sb.setColor(new Color(1, 1, 1, c.a));
            sb.setBlendFunction(770, 771);

            float beeX = x + cX;
            float beeY = y + cY;

            sb.draw(BEE_IMG, beeX - BEE_IMG.getWidth()/2f, beeY - BEE_IMG.getHeight()/2f, BEE_IMG.getWidth()/2f,
                    BEE_IMG.getHeight()/2f, BEE_IMG.getWidth(), BEE_IMG.getHeight(), scale, scale, rotation, 0, 0,
                    BEE_IMG.getWidth(), BEE_IMG.getHeight(), false, false);
        }
    }

    public SwarmOfBees()
    {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH_O);
        showEvokeValue = false;

        generateBees();

        applyFocus();
        updateDescription();
    }

    private void generateBees() {
        bees.clear();
        for (int i = 0; i < BEE_COUNT; i++) {
            Bee newBee = new Bee();
            bees.add(newBee);
        }
    }

    @Override
    public void applyFocus() {
        AbstractPower power = adp().getPower(FocusPower.POWER_ID);
        if (power != null) {
            passiveAmount = Math.max(0, basePassiveAmount + power.amount);
            evokeAmount = Math.max(0, baseEvokeAmount + power.amount);
        } else {
            passiveAmount = basePassiveAmount;
            evokeAmount = baseEvokeAmount;
        }

        if (passiveAmount < 0)
            passiveAmount = 0;
        if (evokeAmount < 0)
            evokeAmount = 0;
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.playV(SpireAnniversary5Mod.BEES_KEY, 0.75f);
    }

    @Override
    public void onEndOfTurn() {
        for (AbstractMonster m : Wiz.getEnemies()) {
            atb(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    if (m == null || m.isDeadOrEscaped())
                        return;
                    DamageInfo info = new DamageInfo(adp(), SwarmOfBees.applyLockOn(m, passiveAmount), DamageInfo.DamageType.THORNS);
                    AbstractGameAction action = new DamageAction(m, info, Wiz.getRandomSlash());
                    ColoredDamagePatch.DamageActionColorField.damageColor.set(action, STING_COLOR);
                    ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
                    att(action);
                }
            });
        }
    }

    @Override
    public void onEvoke() {
        for (AbstractMonster m : Wiz.getEnemies()) {
            DamageInfo info = new DamageInfo(adp(), SwarmOfBees.applyLockOn(m, evokeAmount), DamageInfo.DamageType.THORNS);
            AbstractGameAction action = new DamageAction(m, info, Wiz.getRandomSlash());
            ColoredDamagePatch.DamageActionColorField.damageColor.set(action, STING_COLOR);
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.SLOW);
            att(action);
        }
    }

    @Override
    public void updateAnimation() {
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

        hb.move(cX, cY);

        for (Bee bee : bees)
            bee.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for (Bee bee : bees)
            bee.render(sb);

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
        return new SwarmOfBees();
    }
}