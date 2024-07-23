package thePackmaster.powers.pinnaclepack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Capacitor extends AbstractPackmasterPower {

    public static final String POWER_ID = makeID("Capacitor");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public Capacitor(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,false,owner,amount);
        updateDescription();
    }

    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    public void onInitialApplication() {
        super.onInitialApplication();
        BaseMod.MAX_HAND_SIZE += this.amount;
    }

    public void onRemove() {
        super.onRemove();
        BaseMod.MAX_HAND_SIZE -= this.amount;
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        BaseMod.MAX_HAND_SIZE += stackAmount;
    }

    public void reducePower(int reduceAmount) {
        super.reducePower(reduceAmount);
        BaseMod.MAX_HAND_SIZE -= reduceAmount;
    }

    public void onVictory() {
        super.onVictory();
        BaseMod.MAX_HAND_SIZE -= this.amount;
    }

    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
    }

}
