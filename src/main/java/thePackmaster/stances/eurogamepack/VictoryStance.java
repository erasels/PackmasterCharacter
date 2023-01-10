package thePackmaster.stances.eurogamepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import com.megacrit.cardcrawl.actions.animations.VFXAction;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import thePackmaster.vfx.eurogamepack.VictoryParticleEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class VictoryStance extends AbstractStance {

    public static final String STANCE_ID = makeID("Victory");
    private static long sfxId = -1L;

    public VictoryStance() {
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
                AbstractDungeon.effectsQueue.add(new VictoryParticleEffect());
            }
        }

     //   this.particleTimer2 -= Gdx.graphics.getDeltaTime();
     //   if (this.particleTimer2 < 0.0F) {
     //       this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
     //       AbstractDungeon.effectsQueue.add(new VictoryStanceAuraEffect());
     //   }

    }


    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            stopIdleSfx();
        }

        CardCrawlGame.sound.play("MONSTER_DONU_DEFENSE");

        AbstractDungeon.actionManager.addToTop(new VFXAction(AbstractDungeon.player, new IntenseZoomEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, false), 0.05F, true));
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.GOLDENROD, true));
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));

    }
    public float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? (float)Math.round(damage * 1.5F) : damage;
    }


    public void atStartOfTurn() {
        AbstractDungeon.actionManager.addToBottom(new ChangeStanceAction("Neutral"));
    }

    @Override
    public void onExitStance() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 2));
    }

    @Override
    public void updateDescription() {
        this.description = CardCrawlGame.languagePack.getStanceString(STANCE_ID).DESCRIPTION[0];
    }
}

