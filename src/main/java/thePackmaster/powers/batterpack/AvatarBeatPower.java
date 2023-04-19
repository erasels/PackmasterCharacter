package thePackmaster.powers.batterpack;


import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class AvatarBeatPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("AvatarBeatPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public int carddraw = 1;

    public AvatarBeatPower(AbstractCreature owner, int amount) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);
        isTwoAmount = true;
        amount2 = 10;
        updateDescription();
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        this.carddraw++;
    }

    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount >= amount2 && target != this.owner && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, this.amount), this.amount));
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(this.carddraw));
            this.amount2+=5;
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        if (carddraw == 1)
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + carddraw + DESCRIPTIONS[3];
        else
        description = DESCRIPTIONS[0] + amount2 + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2] + carddraw + DESCRIPTIONS[4];
    }
}




