package thePackmaster.orbs.summonspack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.powers.SadisticPower;
import com.megacrit.cardcrawl.vfx.BobEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.orbs.AbstractPackMasterOrb;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.adp;
import static thePackmaster.util.Wiz.att;

public class EvilSpirit extends AbstractPackMasterOrb implements OnApplyPowerOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(EvilSpirit.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/orbs/summonsPack/EvilSpirit.png");
    private static final float SPIRIT_WIDTH = 96.0f;

    private final static int BASE_PASSIVE = 4;
    private final static int BASE_EVOKE = 2;

    private Color color = Color.WHITE.cpy();
    public static final Color JINX_COLOR = new Color(0, 0, 204f/255f, 1f);
    private double colorTime = 0d;

    private BobEffect ghostBobEffect = new BobEffect(4f, 3.2f);

    public EvilSpirit()
    {
        super(ORB_ID, NAME, BASE_PASSIVE, BASE_EVOKE, "", "", IMG_PATH);
        applyFocus();
        updateDescription();
        bobEffect.dist = 8f * Settings.scale;
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play(SpireAnniversary5Mod.EVIL_KEY, 0.1f);
    }

    @Override
    public void onApplyPower(AbstractCreature target, AbstractPower power) {
        if (target != null && target != adp() && power.type == AbstractPower.PowerType.DEBUFF) {
            DamageInfo info = new DamageInfo(adp(), passiveAmount, DamageInfo.DamageType.THORNS);
            DamageAction action = new DamageAction(target, info, SpireAnniversary5Mod.Enums.EVIL);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(action, JINX_COLOR);
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(action, ColoredDamagePatch.FadeSpeed.NONE);
            att(action);
        }
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
        } else {
            passiveAmount = basePassiveAmount + pow.amount;
            evokeAmount = baseEvokeAmount + pow.amount;
        }
        if (passiveAmount < 0)
            passiveAmount = 0;
        if (evokeAmount < 0)
            evokeAmount = 0;
    }

    @Override
    public void onEvoke() {
        Wiz.applyToSelfTop(new SadisticPower(adp(), evokeAmount));
    }

    @Override
    public void updateAnimation() {
        ghostBobEffect.update();
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
        colorTime += Gdx.graphics.getDeltaTime();
        color.set(1f, 1f, 1f, 0.75f + (float)(0.25f*Math.cos(colorTime * 0.65f * Math.PI)));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(color);
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - SPIRIT_WIDTH /2F, cY - SPIRIT_WIDTH /2F + ghostBobEffect.y, SPIRIT_WIDTH /2F, SPIRIT_WIDTH /2F,
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
        return new EvilSpirit();
    }
}