package thePackmaster.orbs.summonspack;

import basemod.abstracts.CustomOrb;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.OrbStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.FocusPower;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.*;

public class Bulldog extends CustomOrb {
    public static final String ORB_ID = SpireAnniversary5Mod.makeID(Bulldog.class.getSimpleName());
    private static final OrbStrings orbString = CardCrawlGame.languagePack.getOrbString(ORB_ID);
    public static final String NAME = orbString.NAME;
    public static final String[] DESCRIPTIONS = orbString.DESCRIPTION;
    private static final String IMG_PATH = makePath("/images/vfx/summonspack/Bulldog.png");
    private static final float BULLDOG_WIDTH = 96.0f;
    private static final Color TEXT_COLOR = new Color(1.0f, 0.25f, 0.25f, 1.0f);

    private float rotation;

    public Bulldog(int passive)
    {
        super(ORB_ID, NAME, passive, passive, "", "", IMG_PATH);
        basePassiveAmount = passive;
        baseEvokeAmount = basePassiveAmount;
        showEvokeValue = false;
        rotation = 0.0f;
        applyFocus();
        updateDescription();
    }

    public void applyFocus() {
        AbstractPower power = adp().getPower(FocusPower.POWER_ID);
        if (power != null)
            passiveAmount = Math.max(0, basePassiveAmount + power.amount);
        else
            passiveAmount = basePassiveAmount;

        evokeAmount = passiveAmount;
    }

    @Override
    public void playChannelSFX() {
        CardCrawlGame.sound.play("ORB_SLOT_GAIN", 0.1f);
    }

    @Override
    public void onEvoke() {
        AbstractMonster m = Wiz.getLowestHealthEnemy();
        vfx(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F);
        doDmg(m, evokeAmount, DamageInfo.DamageType.THORNS);
    }

    @Override
    public void onEndOfTurn() {
        AbstractMonster m = Wiz.getLowestHealthEnemy();
        vfx(new BiteEffect(m.hb.cX, m.hb.cY - 40.0F * Settings.scale, Color.SCARLET.cpy()), 0.3F);
        doDmg(m, evokeAmount, DamageInfo.DamageType.THORNS);
    }

    @Override
    public void updateAnimation() {
        rotation += 0.5f*Gdx.graphics.getDeltaTime()*360.0f;
        super.updateAnimation();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, cX - BULLDOG_WIDTH /2F, cY - BULLDOG_WIDTH /2F, BULLDOG_WIDTH /2F, BULLDOG_WIDTH /2F,
                BULLDOG_WIDTH, BULLDOG_WIDTH, scale, scale, rotation, 0, 0, (int) BULLDOG_WIDTH, (int) BULLDOG_WIDTH,
                false, false);
        renderText(sb);
        hb.render(sb);
    }

    @Override
    protected void renderText(SpriteBatch sb) {
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, Integer.toString(passiveAmount),
                cX + NUM_X_OFFSET + 25*Settings.scale, cY + NUM_Y_OFFSET - 25* Settings.yScale,
                TEXT_COLOR, fontScale);
    }

    @Override
    public void updateDescription() {
        applyFocus();
        description = DESCRIPTIONS[0] + passiveAmount + DESCRIPTIONS[1] + evokeAmount + DESCRIPTIONS[2];
    }

    @Override
    public AbstractOrb makeCopy() {
        return new Bulldog(passiveAmount);
    }
}