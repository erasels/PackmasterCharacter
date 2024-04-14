package thePackmaster.powers.showmanpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.LaserBeamEffect;
import com.megacrit.cardcrawl.vfx.combat.SmallLaserEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.effects.monsterhunterpack.LaserBeamEffectReversed;
import thePackmaster.effects.showmanpack.MagicCylinderEffect;
import thePackmaster.effects.showmanpack.MediumLaserBeamEffectReversed;
import thePackmaster.powers.AbstractPackmasterPower;

public class MagicCylinderPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("MagicCylinderPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public MagicCylinderPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1){
            this.description = DESCRIPTIONS[0];
        }
        else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner && damageAmount >= owner.currentBlock) {
            addToBot(new VFXAction(new MagicCylinderEffect(owner.hb.cX + (100f * Settings.scale), owner.hb.cY + (80f * Settings.scale), owner.hb.cX + (100f * Settings.scale), owner.hb.cY + (120f * Settings.scale), 0.5f)));
            addToBot(new WaitAction(0.5f));
            addToBot(new VFXAction(new MagicCylinderEffect(owner.hb.cX + (100f * Settings.scale), owner.hb.cY - (80f * Settings.scale), owner.hb.cX + (100f * Settings.scale), owner.hb.cY - (120f * Settings.scale), 0.5f)));
            addToBot(new WaitAction(0.5f));
            addToBot(new VFXAction(new MediumLaserBeamEffectReversed(owner.hb.cX + (100f * Settings.scale), owner.hb.cY - (120f * Settings.scale), Color.LIME, 0.4f)));
            addToBot(new WaitAction(0.5f));
            addToBot(new DamageAction(info.owner, new DamageInfo(owner, damageAmount, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.NONE));
            flash();
            addToBot(new ReducePowerAction(owner, owner, this, 1));
            if (this.amount <= 0){
                addToBot(new RemoveSpecificPowerAction(owner, owner, this));
            }
            return 0;
        }
        return damageAmount;
    }

}
