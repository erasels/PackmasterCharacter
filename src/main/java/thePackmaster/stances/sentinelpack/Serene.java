package thePackmaster.stances.sentinelpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.unique.DoubleYourBlockAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.stances.NeutralStance;
import com.megacrit.cardcrawl.stances.WrathStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;
import com.megacrit.cardcrawl.vfx.stance.StanceAuraEffect;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.downfallpack.AncientStanceParticleEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Serene extends AbstractStance {
    public static final String STANCE_ID = makeID("Serene");
    private static long sfxId = -1L;

    public Serene() {
        this.ID = STANCE_ID;
        this.name = CardCrawlGame.languagePack.getStanceString(STANCE_ID).NAME;
        this.updateDescription();
    }

    @Override
    public void updateAnimation() {
            if (!Settings.DISABLE_EFFECTS) {
                this.particleTimer -= Gdx.graphics.getDeltaTime();
                if (this.particleTimer < 0.0F) {
                    this.particleTimer = 0.04F;
                    AbstractDungeon.effectsQueue.add(new AncientStanceParticleEffect(new Color(.5F, 0.5F, 1F, 0.0F)));
                }
            }

            this.particleTimer2 -= Gdx.graphics.getDeltaTime();
            if (this.particleTimer2 < 0.0F) {
                this.particleTimer2 = MathUtils.random(0.45F, 0.55F);
                AbstractDungeon.effectsQueue.add(new StanceAuraEffect("SereneStance"));
            }
    }

    @Override
    public void onEnterStance() {
        if (sfxId != -1L) {
            stopIdleSfx();
        }

        CardCrawlGame.sound.play("POWER_MANTRA");
        for (int i = 0; i < 30; i++) {
            AbstractDungeon.effectsQueue.add(new StanceAuraEffect(WrathStance.STANCE_ID));
        }
    }

    @Override
    public void atStartOfTurn() {
        Wiz.atb(new ChangeStanceAction(NeutralStance.STANCE_ID));
    }

    @Override
    public void onExitStance() {
        Wiz.atb(new VFXAction(AbstractDungeon.player, new IntenseZoomEffect(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY, false), 0.05F, true));
        Wiz.atb(new DoubleYourBlockAction(Wiz.p()));
        Wiz.vfx(new BorderFlashEffect(Color.CYAN, true));
    }

    @Override
    public void updateDescription() {
        this.description = CardCrawlGame.languagePack.getStanceString(STANCE_ID).DESCRIPTION[0];
    }

    public static int receiveOnPlayerLoseBlock(int i) {
        if(Wiz.p().stance.ID.equals(STANCE_ID)) {
            i = 0;
        }
        return i;
    }

    }
