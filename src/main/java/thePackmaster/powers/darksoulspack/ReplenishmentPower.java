package thePackmaster.powers.darksoulspack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ReplenishmentPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("ReplenishmentPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    private boolean appliedThisTurn = false;


    public ReplenishmentPower(AbstractCreature owner, int amount){
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
        updateDescription();
    }

    public void atStartOfTurn(){
        appliedThisTurn = false;
    }

    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (!appliedThisTurn && !AbstractDungeon.actionManager.turnHasEnded) {
            this.flash();
            Wiz.applyToSelf(new EndTurnRestorePower(owner, amount));
            appliedThisTurn = true;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
