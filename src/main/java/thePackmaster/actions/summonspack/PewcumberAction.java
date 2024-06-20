package thePackmaster.actions.summonspack;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.patches.ColoredDamagePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;

import static thePackmaster.SpireAnniversary5Mod.makePath;
import static thePackmaster.util.Wiz.adp;

public class PewcumberAction extends AbstractGameAction {
    private AbstractMonster m;
    private static final float DURATION = 1F;
    private static final String CUCUMBER_PATH = makePath("images/vfx/summonspack/vegetables/Cucumber.png");
    private static final String POTATO_PATH = makePath("images/vfx/summonspack/vegetables/Potato.png");
    private static final String EGGPLANT_PATH = makePath("images/vfx/summonspack/vegetables/Eggplant.png");
    private static final String CARROT_PATH = makePath("images/vfx/summonspack/vegetables/Carrot.png");
    private static final String TOMATO_PATH = makePath("images/vfx/summonspack/vegetables/Tomato.png");
    private static final String LETTUCE_PATH = makePath("images/vfx/summonspack/vegetables/Lettuce.png");
    private static final String ONION_PATH = makePath("images/vfx/summonspack/vegetables/Onion.png");
    private static final String BROCCOLI_PATH = makePath("images/vfx/summonspack/vegetables/Broccoli.png");
    private static final String BELL_GREEN_PATH = makePath("images/vfx/summonspack/vegetables/BellPepperGreen.png");
    private static final String BELL_RED_PATH = makePath("images/vfx/summonspack/vegetables/BellPepperRed.png");
    private static final String BELL_YELLOW_PATH = makePath("images/vfx/summonspack/vegetables/BellPepperYellow.png");
    private static final String BELL_ORANGE_PATH = makePath("images/vfx/summonspack/vegetables/BellPepperOrange.png");

    private static final float AIR_TIME = 0.3f;
    private static final float FADE_TIME = 0.6f;
    
    private AbstractPlayer p = adp();
    private DamageInfo info;
    private boolean thunkEffect;
    private Texture vegetableImage;
    private boolean pew;

    public PewcumberAction(AbstractMonster monster, DamageInfo info, boolean pew) {
        this.m = monster;
        this.info = info;
        this.pew = pew;
        actionType = ActionType.DAMAGE;
        duration = DURATION;
        thunkEffect = false;

        // I'm sure there's a better way but this is fine
        int x = MathUtils.random(0, 9);
        if (x == 0) {
            vegetableImage = TexLoader.getTexture(CUCUMBER_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, new Color(0, 0.6f, 0, 1f));
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else if (x == 1) {
            vegetableImage = TexLoader.getTexture(POTATO_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.TAN.cpy());
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else if (x == 2) {
            vegetableImage = TexLoader.getTexture(EGGPLANT_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, new Color(0.5f, 0, 0.6f, 1));
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else if (x == 3) {
            vegetableImage = TexLoader.getTexture(CARROT_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.ORANGE.cpy());
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else if (x == 4) {
            vegetableImage = TexLoader.getTexture(ONION_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, new Color(1f, 0.95f, 0.85f, 1f));
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else if (x == 5) {
            vegetableImage = TexLoader.getTexture(BROCCOLI_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.GREEN.cpy());
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else if (x == 6) {
            vegetableImage = TexLoader.getTexture(TOMATO_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.RED.cpy());
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else if (x == 7) {
            vegetableImage = TexLoader.getTexture(LETTUCE_PATH);
            ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.GREEN.cpy());
            ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
        }
        else {
            int y = MathUtils.random(0, 3);
            if (y == 0) {
                vegetableImage = TexLoader.getTexture(BELL_GREEN_PATH);
                ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.GREEN.cpy());
                ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
            }
            else if (y == 1) {
                vegetableImage = TexLoader.getTexture(BELL_RED_PATH);
                ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.RED.cpy());
                ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
            }
            else if (y == 2) {
                vegetableImage = TexLoader.getTexture(BELL_YELLOW_PATH);
                ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.YELLOW.cpy());
                ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
            }
            else {
                vegetableImage = TexLoader.getTexture(BELL_ORANGE_PATH);
                ColoredDamagePatch.DamageActionColorField.damageColor.set(this, Color.ORANGE.cpy());
                ColoredDamagePatch.DamageActionColorField.fadeSpeed.set(this, ColoredDamagePatch.FadeSpeed.NONE);
            }
        }
    }

    public void update() {
        if (m == null || m.isDeadOrEscaped()) {
            isDone = true;
            return;
        }

        float targetX = 0f;
        float targetY = 0f;
        if (duration == DURATION) {
            targetX = m.hb.cX + AbstractDungeon.miscRng.random(-50.0f*Settings.xScale, 50.0f*Settings.xScale);
            targetY = m.hb.cY + AbstractDungeon.miscRng.random(-50.0f*Settings.yScale, 50.0f*Settings.yScale);
            float targetX2 = targetX + AbstractDungeon.miscRng.random(-400.0f*Settings.xScale, 400.0f*Settings.xScale);
            float targetY2 = targetY + AbstractDungeon.miscRng.random(-400.0f*Settings.yScale, 400.0f*Settings.yScale);
            AbstractGameEffect vegetableEffect = new VfxBuilder(vegetableImage, p.hb.cX, p.hb.cY, AIR_TIME)
                    .moveX(p.hb.cX, targetX, VfxBuilder.Interpolations.LINEAR)
                    .moveY(p.hb.cY, targetY, VfxBuilder.Interpolations.LINEAR)
                    .rotate(720.0f)
                    .andThen(FADE_TIME)
                    .moveX(targetX, targetX2, VfxBuilder.Interpolations.LINEAR)
                    .moveY(targetY, targetY2, VfxBuilder.Interpolations.LINEAR)
                    .rotate(360.0f)
                    .fadeOut(FADE_TIME)
                    .build();

            AbstractDungeon.topLevelEffects.add(vegetableEffect);
            if (pew)
                CardCrawlGame.sound.play(SpireAnniversary5Mod.PEW_KEY);
        }

        if (duration <= DURATION - AIR_TIME && !thunkEffect) {
            thunkEffect = true;
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(targetX, targetY, AttackEffect.BLUNT_LIGHT));
            if (m != null && p.currentHealth > 0) {
                m.damage(info);
                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                }
            }
            isDone = true;
        }

        tickDuration();
    }
}
