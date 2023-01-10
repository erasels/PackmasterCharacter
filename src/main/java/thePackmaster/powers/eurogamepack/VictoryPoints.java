package thePackmaster.powers.eurogamepack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.watcher.ChangeStanceAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.stances.eurogamepack.VictoryStance;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class VictoryPoints extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("VictoryPoints");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private int VICTORY_REQUIRED = 100;


    public VictoryPoints(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + this.VICTORY_REQUIRED + DESCRIPTIONS[1];
    }

    public void onAfterCardPlayed(AbstractCard card) {
        this.flash();
        this.addToTop(new ApplyPowerAction(owner, owner, new VictoryPoints(owner, Math.round(this.amount/4))));
    }
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.addToTop(new ApplyPowerAction(owner, owner, new TotalTrackerPower(owner, stackAmount)));
        if (AbstractDungeon.player.hasPower(QuickGamePower.POWER_ID)){
            VICTORY_REQUIRED = Math.round(100 - 25 * AbstractDungeon.player.getPower(QuickGamePower.POWER_ID).amount);
            if (VICTORY_REQUIRED <= 25){VICTORY_REQUIRED = 25;}
        }
        else{VICTORY_REQUIRED = 100;}
        if (this.amount >= VICTORY_REQUIRED) {
            this.addToBot(new ChangeStanceAction(new VictoryStance()));
            this.amount -= VICTORY_REQUIRED;
            this.addToTop(new ApplyPowerAction(owner, owner, new EntranceTrackerPower(owner, 1)));
        }

    }

}
