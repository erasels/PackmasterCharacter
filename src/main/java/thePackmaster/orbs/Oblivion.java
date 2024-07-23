package thePackmaster.orbs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import thePackmaster.actions.OblivionEvokeAction;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.util.TexLoader;
import thePackmaster.vfx.distortionpack.PixelEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.makeImagePath;

public class Oblivion extends AbstractOrb implements DiscardHookPatch.OnDiscardThing, PackmasterOrb {
    public static final String ORB_ID = makeID("Oblivion");
    private static final OrbStrings orbStrings = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    private static final String[] DESC = orbStrings.DESCRIPTION;

    private static final float vfxRange = 32f * Settings.scale;

    private static final Color EVOKE_TEXT_COLOR = new Color(0.2F, 1.0F, 1.0F, 1);

    private float jumpTimer;

    public Oblivion() {
        this.ID = ORB_ID;
        this.name = orbStrings.NAME;

        this.basePassiveAmount = this.passiveAmount = 2;
        this.baseEvokeAmount = this.evokeAmount = 4;

        jumpTimer = MathUtils.random(0.2f, 1.0f);

        this.img = TexLoader.getTexture(makeImagePath("orbs/Oblivion.png"));
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.applyFocus();
        this.description = DESC[0] + this.passiveAmount + DESC[1] + this.evokeAmount + DESC[2];
    }

    public void applyFocus() {
        AbstractPower power = AbstractDungeon.player.getPower(FocusPower.POWER_ID);
        if (power != null) {
            this.passiveAmount = Math.max(0, this.basePassiveAmount + power.amount);
        } else {
            this.passiveAmount = this.basePassiveAmount;
        }
    }

    @Override
    public void onManualDiscardThing() {
        passiveEffect();
    }

    @Override
    public void passiveEffect() {
        this.evokeAmount += this.passiveAmount;
        this.updateDescription();

        int amt = MathUtils.random(4, 7);
        for (int i = 0; i < amt; ++i)
            AbstractDungeon.effectList.add(new PixelEffect(MathUtils.randomBoolean(), vfxRange, (int) (vfxRange / 2)).setPos(this.cX, this.cY));
        this.angle = MathUtils.random(360f);
    }

    @Override
    public void onEvoke() {
        AbstractDungeon.actionManager.addToTop(new OblivionEvokeAction(this.evokeAmount));
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Oblivion();
    }

    @Override
    public void updateAnimation() {
        super.updateAnimation();

        this.angle += Gdx.graphics.getDeltaTime() * 180.0F;

        this.jumpTimer -= Gdx.graphics.getDeltaTime();
        if (this.jumpTimer < 0.0F) {
            this.angle = MathUtils.random(360f);
            int amt = MathUtils.random(3, 5);
            for (int i = 0; i < amt; ++i)
                AbstractDungeon.effectList.add(new PixelEffect(MathUtils.randomBoolean(), vfxRange, (int) (vfxRange / 2)).setPos(this.cX, this.cY));
            jumpTimer = MathUtils.random(0.4f, 2f) + MathUtils.random(0.4f, 2f);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(this.c);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale, this.scale, this.angle, 0, 0, 96, 96, false, false);
        this.shineColor.a = this.c.a / 4.0F;
        sb.setColor(this.shineColor);
        sb.setBlendFunction(770, 1);
        sb.draw(this.img, this.cX - 48.0F, this.cY - 48.0F + this.bobEffect.y, 48.0F, 48.0F, 96.0F, 96.0F, this.scale * 1.1F, this.scale * 1.1F, this.angle * 1.33f, 0, 0, 96, 96, false, false);
        sb.setBlendFunction(770, 771);
        this.renderText(sb);
        this.hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        EVOKE_TEXT_COLOR.a = this.c.a;
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.evokeAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET - 4.0F * Settings.scale, EVOKE_TEXT_COLOR, this.fontScale);
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(this.passiveAmount), this.cX + NUM_X_OFFSET, this.cY + this.bobEffect.y / 2.0F + NUM_Y_OFFSET + 20.0F * Settings.scale, this.c, this.fontScale);
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.playA("ORB_DARK_CHANNEL", 0.5F + MathUtils.random(0.2F));
    }
}
