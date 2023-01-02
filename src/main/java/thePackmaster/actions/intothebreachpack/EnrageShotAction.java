package thePackmaster.actions.intothebreachpack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.StunMonsterAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import thePackmaster.cards.intothebreachpack.EnrageShot;

public class EnrageShotAction extends AbstractGameAction {
    AbstractMonster targetMonster;

    public EnrageShotAction(int strengthApply, AbstractMonster m) {
        this.amount = strengthApply;
        this.targetMonster = m;
    }

    public void update() {
        if (targetMonster != null && targetMonster.getIntentBaseDmg() < 0) {
            addToBot(new StunMonsterAction(targetMonster, AbstractDungeon.player));
            addToBot(new ApplyPowerAction(targetMonster, AbstractDungeon.player, new StrengthPower(targetMonster, amount), amount));
        } else
            AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F, CardCrawlGame.languagePack.getCardStrings(EnrageShot.ID).EXTENDED_DESCRIPTION[0], true));
        this.isDone = true;
    }
}
