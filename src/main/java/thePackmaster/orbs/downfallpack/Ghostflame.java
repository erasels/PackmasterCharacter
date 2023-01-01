package thePackmaster.orbs.downfallpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.vfx.GhostlyWeakFireEffect;
import com.megacrit.cardcrawl.vfx.combat.*;
import com.megacrit.cardcrawl.vfx.combat.OrbFlareEffect.OrbFlareColor;
import thePackmaster.actions.downfallpack.GhostflameOrbEvokeAction;

import static thePackmaster.util.Wiz.atb;

public class Ghostflame extends AbstractOrb {
    private static final OrbStrings orbString;
    private final float vfxTimer = 1.0F;
    private boolean ignited = false;

    private com.badlogic.gdx.graphics.Color color = com.badlogic.gdx.graphics.Color.CYAN.cpy();
    private com.badlogic.gdx.graphics.Color color2 = com.badlogic.gdx.graphics.Color.TEAL.cpy();

    private float particleTimer = 0.0F;

    public Ghostflame() {
        this.ID = "Ghostflame";
        this.img = ImageMaster.ORB_LIGHTNING;
        this.name = orbString.NAME;
        this.baseEvokeAmount = 8;
        this.evokeAmount = this.baseEvokeAmount;
        this.basePassiveAmount = 2;
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
        if (ignited) {
            AbstractDungeon.actionManager.addToTop(new GhostflameOrbEvokeAction(new DamageInfo(AbstractDungeon.player, this.evokeAmount, DamageType.THORNS)));
        }
    }

    public void onEndOfTurn() {
            float speedTime = 0.2F / (float)AbstractDungeon.player.orbs.size();
            if (Settings.FAST_MODE) {
                speedTime = 0.0F;
            }
            OrbFlareEffect flare = new OrbFlareEffect(this, OrbFlareColor.LIGHTNING);
            ReflectionHacks.setPrivate(flare, OrbFlareEffect.class, "color", color);
            ReflectionHacks.setPrivate(flare, OrbFlareEffect.class, "color2", color2);
            AbstractDungeon.actionManager.addToBottom(new VFXAction(flare, speedTime));
            AbstractDungeon.actionManager.addToBottom(new GhostflameOrbEvokeAction(new DamageInfo(AbstractDungeon.player, this.passiveAmount, DamageType.THORNS)));

            atb(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                ignited = true;
            }
        });


    }

    public void triggerEvokeAnimation() {
        CardCrawlGame.sound.play("GHOST_ORB_IGNITE_1", 0.1F);
        AbstractDungeon.effectsQueue.add(new GhostIgniteEffect(this.cX, this.cY));
    }

    public void updateAnimation() {
        super.updateAnimation();
        this.particleTimer -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer < 0.0F) {
            AbstractDungeon.effectList.add(new GhostlyWeakFireEffect(this.cX, this.cY));
            this.particleTimer = 0.06F;
        }

    }

    public void render(SpriteBatch sb) {
        this.shineColor.a = this.c.a / 6.0F;
        sb.setColor(this.shineColor);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, this.scale * 1.2F, this.angle, 0, 0, 96, 96, false, false);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.2F, this.scale + MathUtils.sin(this.angle / 12.566371F) * 0.05F + 0.19634955F, -this.angle, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle / 12.0F, 0, 0, 96, 96, false, false);
        this.renderText(sb);
        this.hb.render(sb);
    }

    public void playChannelSFX() {
        if (MathUtils.randomBoolean()) {
        CardCrawlGame.sound.play("GHOST_ORB_IGNITE_1", 0.3F);
    } else {
        CardCrawlGame.sound.play("GHOST_ORB_IGNITE_2", 0.3F);
    }
    }

    public AbstractOrb makeCopy() {
        return new Ghostflame();
    }

    static {
        orbString = CardCrawlGame.languagePack.getOrbString("Lightning");
    }
}
