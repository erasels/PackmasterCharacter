package thePackmaster.stances.aggressionpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.StanceStrings;
import com.megacrit.cardcrawl.stances.AbstractStance;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.vfx.aggressionpack.AggressionStanceAuraEffect;
import thePackmaster.vfx.aggressionpack.AggressionStanceParticleGenerator;
import thePackmaster.vfx.aggressionpack.WanderingFire;

import java.util.ArrayList;
import java.util.List;

public class AggressionStance extends AbstractStance {
    public static final String STANCE_ID = SpireAnniversary5Mod.makeID("Aggression");
    private static final StanceStrings stanceStrings = CardCrawlGame.languagePack.getStanceString(STANCE_ID);
    private static final String NAME = stanceStrings.NAME;
    private static final String[] DESCRIPTION = stanceStrings.DESCRIPTION;

    public static final int ADDITIONAL_DAMAGE_PERCENT = 25;

    private List<WanderingFire> wanderingFires;

    public AggressionStance() {
        this.ID = STANCE_ID;
        this.name = NAME;
        this.updateDescription();
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTION[0].replace("{0}", ADDITIONAL_DAMAGE_PERCENT + "");
    }

    @Override
    public final float atDamageGive(float damage, DamageInfo.DamageType type) {
        return type == DamageInfo.DamageType.NORMAL ? damage * (100 + additionalDamagePercent()) / 100.0f : damage;
    }

    public int additionalDamagePercent() {
        return ADDITIONAL_DAMAGE_PERCENT;
    }

    @Override
    public void onEnterStance() {
        CardCrawlGame.sound.play("ATTACK_FLAME_BARRIER");
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.FIREBRICK, true));
        AbstractDungeon.effectsQueue.add(new AggressionStanceParticleGenerator(AbstractDungeon.player.hb.cX, AbstractDungeon.player.hb.cY));
    }

    @Override
    public void onExitStance() {
        if (this.wanderingFires != null) {
            this.wanderingFires.forEach(f -> f.hidden = true);
        }
    }
    @Override
    public final void updateAnimation() {
        this.updateParticleEffect();

        this.particleTimer2 -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer2 < 0.0F) {
            this.particleTimer2 = this.getAuraEffectInterval();
            AbstractDungeon.effectsQueue.add(new AggressionStanceAuraEffect());
        }
    }

    public void updateParticleEffect() {
        if (Settings.DISABLE_EFFECTS) {
            return;
        }
        if (this.wanderingFires == null) {
            this.wanderingFires = new ArrayList<>();
            this.wanderingFires.add(new WanderingFire(AbstractDungeon.player.hb.cX + 40.0F * Settings.scale, AbstractDungeon.player.hb.cY + 20.0F * Settings.scale));
        }
        for (WanderingFire wanderingFire : this.wanderingFires) {
            wanderingFire.update();
        }
    }

    public float getAuraEffectInterval() {
        return MathUtils.random(0.3F, 0.4F);
    }
}
