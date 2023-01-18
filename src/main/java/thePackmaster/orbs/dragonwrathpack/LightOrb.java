package thePackmaster.orbs.dragonwrathpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.GhostlyFireEffect;
import com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect;
import com.megacrit.cardcrawl.vfx.combat.*;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.downfallpack.GhostflameOrbEvokeAction;
import thePackmaster.powers.dragonwrathpack.confessionpower;
import thePackmaster.util.TexLoader;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeOrbPath;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.p;

public class LightOrb extends AbstractOrb {
    private float vfxTimer = 1.0f;
    private float vfxIntervalMin = 0.1f;
    private float vfxIntervalMax = 0.4f;
    private static final float ORB_WAVY_DIST = 0.04f;
    private static final float PI_4 = 12.566371f;
    public static final String ORB_ID = SpireAnniversary5Mod.makeID("Light");
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static com.badlogic.gdx.graphics.Color color = Color.GOLDENROD.cpy();
    public static com.badlogic.gdx.graphics.Color color2 = Color.SKY.cpy();

    private float particleTimer = 0.0F;

    public LightOrb() {
        this.ID = ORB_ID;
        img = TexLoader.getTexture(makeOrbPath("Light.png"));
        this.name = orbString.NAME;
        baseEvokeAmount = 3;
        this.basePassiveAmount = 1;
        this.passiveAmount = this.basePassiveAmount;
        this.updateDescription();
        this.angle = MathUtils.random(360.0F);
        this.channelAnimTimer = 0.5F;
        scale = 1.5F;
    }

    public void updateDescription() {
        this.applyFocus();
        this.description = orbString.DESCRIPTION[0] + this.passiveAmount + orbString.DESCRIPTION[1] + this.evokeAmount + orbString.DESCRIPTION[2];
    }

    public void onEvoke() {
        OrbFlareEffect flare = new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.LIGHTNING);
        ReflectionHacks.setPrivate(flare, AbstractGameEffect.class, "color", color);
        ReflectionHacks.setPrivate(flare, OrbFlareEffect.class, "color2", color2);
        Wiz.vfx(new MiracleEffect(color,color2,"POWER_MANTRA"));
        Wiz.applyToSelf(new confessionpower(AbstractDungeon.player,evokeAmount));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player,new DamageInfo(AbstractDungeon.player,evokeAmount,
                DamageInfo.DamageType.THORNS)));
    }

    public void onEndOfTurn() {
        float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
        if (Settings.FAST_MODE) {
            speedTime = 0.0F;
        }
        OrbFlareEffect flare = new OrbFlareEffect(this, OrbFlareEffect.OrbFlareColor.LIGHTNING);
        ReflectionHacks.setPrivate(flare, AbstractGameEffect.class, "color", color);
        ReflectionHacks.setPrivate(flare, OrbFlareEffect.class, "color2", color2);
        Wiz.vfx(new MiracleEffect(color,color2,"HEAL_3"));
        Wiz.applyToSelf(new confessionpower(AbstractDungeon.player,passiveAmount));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(AbstractDungeon.player,new DamageInfo(AbstractDungeon.player,passiveAmount,
                DamageInfo.DamageType.THORNS)));
    }

    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    public void updateAnimation() {
        super.updateAnimation();
        angle += Gdx.graphics.getDeltaTime() * 45.0f;
        vfxTimer -= Gdx.graphics.getDeltaTime();
        if (vfxTimer < 0.0f) {
            AbstractDungeon.effectList.add(new LightningOrbPassiveEffect(cX, cY)); // This is the purple-sparkles in the orb. You can change this to whatever fits your orb.
            vfxTimer = MathUtils.random(vfxIntervalMin, vfxIntervalMax);
        }

    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, c.a / 2.0f));
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, scale, angle, 0, 0, 96, 96, false, false);
        sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.c.a / 2.0f));
        sb.setBlendFunction(770, 1);
        sb.draw(img, cX - 48.0f, cY - 48.0f + bobEffect.y, 48.0f, 48.0f, 96.0f, 96.0f, scale, scale + MathUtils.sin(angle / PI_4) * ORB_WAVY_DIST * Settings.scale, -angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        renderText(sb);
        hb.render(sb);
    }

    public void playChannelSFX() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    public AbstractOrb makeCopy() {
        return new LightOrb();
    }
}

