package thePackmaster.powers.serpentinepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.stances.serpentinepack.VenemousStance;

public class VenemousExitPower extends AbstractPackmasterPower {

    public static final String POWER_ID = SpireAnniversary5Mod.makeID("VenemousExitPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public VenemousExitPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void stackPower(int stackAmount) {
        this.amount = 0;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (AbstractDungeon.player.stance.ID.equals(VenemousStance.STANCE_ID)){
            this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new EmptyStanceEffect(this.owner.hb.cX, this.owner.hb.cY), 0.1F)));
            this.addToBot(new ChangeStanceAction("Neutral"));
        }
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
