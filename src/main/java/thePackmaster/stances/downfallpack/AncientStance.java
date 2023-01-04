package thePackmaster.stances.downfallpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

import com.megacrit.cardcrawl.actions.animations.VFXAction;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.downfallpack.AncientStanceParticleEffect;

import static thePackmaster.util.Wiz.*;
import static thePackmaster.SpireAnniversary5Mod.makeID;


public class AncientStance extends AbstractStance {

    public static final String STANCE_ID = makeID("Ancient");
    private static long sfxId = -1L;

    public AncientStance() {
        this.ID = STANCE_ID;
        this.name = CardCrawlGame.languagePack.getStanceString(STANCE_ID).NAME;
        this.updateDescription();
    }// 24

    @Override
    public void updateAnimation() {

            if (!Settings.DISABLE_EFFECTS) {

                this.particleTimer -= Gdx.graphics.getDeltaTime();
                if (this.particleTimer < 0.0F) {
                    this.particleTimer = 0.04F;
                    AbstractDungeon.effectsQueue.add(new AncientStanceParticleEffect(new Color(1.0F, 0.9F, 0.7F, 0.0F)));
                }
            }

            this.particleTimer2 -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer2 < 0.0F) {
                this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
                AbstractDungeon.effectsQueue.add(new StanceAuraEffect("AncientStance"));
            }

    }


    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            stopIdleSfx();
        }

        CardCrawlGame.sound.play("MONSTER_DONU_DEFENSE");

        AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new IntenseZoomEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, false), 0.05F, true));
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLDENROD, true));

    }



    public void atStartOfTurn() {
        if (AbstractDungeon.player.hasPower(PlatedArmorPower.POWER_ID)){
            if (AbstractDungeon.player.getPower(PlatedArmorPower.POWER_ID).amount < 6){
                applyToSelf(new PlatedArmorPower(AbstractDungeon.player, 2));
            }
        }
    }

    @Override
    public void onExitStance() {
        applyToSelf(new ArtifactPower(Wiz.p(), 1));
    }

    @Override
    public void updateDescription() {
        this.description = CardCrawlGame.languagePack.getStanceString(STANCE_ID).DESCRIPTION[0];
    }
}
