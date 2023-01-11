package thePackmaster.powers.monsterhunterpack;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
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

public class TimePiecePower extends AbstractPackmasterPower {
    // intellij stuff Example, buff, false
    private static final String SIMPLE_NAME = "TimePiecePower";
    public static final String POWER_ID = makeID(SIMPLE_NAME);
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String LOC_NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public TimePiecePower(AbstractCreature owner, int amount) {
        super(POWER_ID, LOC_NAME, PowerType.BUFF, false, owner, amount);
        name = LOC_NAME;
        canGoNegative = false;
        updateDescription();
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        this.amount++;
        if (this.amount > 12){
            addToBot(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, 3), 3));
            this.flash();
            this.amount = 0;
        }
        else {
            this.flashWithoutSound();
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}