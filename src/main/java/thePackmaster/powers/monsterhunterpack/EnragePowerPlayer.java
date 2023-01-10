package thePackmaster.powers.monsterhunterpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EnragePowerPlayer extends AbstractPackmasterPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "EnragePowerPlayer";
    public static final String POWER_ID = makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EnragePowerPlayer(AbstractCreature owner, int amount) {
        super(POWER_ID, LOC_NAME, PowerType.BUFF, false, owner, amount);
        name = LOC_NAME;
        canGoNegative = false;
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        boolean applyStr = true;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters){
            boolean isAttacking =
                    (m.intent == AbstractMonster.Intent.ATTACK) ||
                    (m.intent == AbstractMonster.Intent.ATTACK_DEBUFF) ||
                    (m.intent == AbstractMonster.Intent.ATTACK_BUFF) ||
                    (m.intent == AbstractMonster.Intent.ATTACK_DEFEND);
            if (isAttacking){
                applyStr = false;
            }
        }
        if (applyStr){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount * 10 + DESCRIPTIONS[1];
    }
}
