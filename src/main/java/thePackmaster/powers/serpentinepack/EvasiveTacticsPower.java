package thePackmaster.powers.serpentinepack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.actions.watcher.NotStanceCheckAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.GenericSmokeEffect;
import com.megacrit.cardcrawl.vfx.combat.EmptyStanceEffect;
import com.megacrit.cardcrawl.vfx.combat.SmokeBlurEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.stances.serpentinepack.VenemousStance;

public class EvasiveTacticsPower extends AbstractPackmasterPower {

    public static final String POWER_ID = SpireAnniversary5Mod.makeID("EvasiveTacticsPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EvasiveTacticsPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void updateDescription(){
        super.updateDescription();
        this.description = DESCRIPTIONS[0]+amount+DESCRIPTIONS[1];
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        this.addToBot(new NotStanceCheckAction("Neutral", new VFXAction(new GenericSmokeEffect(this.owner.hb.cX, this.owner.hb.cY), 0.1F)));
        this.addToBot(new NotStanceCheckAction("Neutral", new GainBlockAction(this.owner, amount)));
        this.addToBot(new ChangeStanceAction("Neutral"));
    }
}
