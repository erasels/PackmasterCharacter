package thePackmaster.powers.cthulhupack;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AllSeeingPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("AllSeeingPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String DESCRIPTIONS[] = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public AllSeeingPower(AbstractCreature owner, int amount, int damage) {
        super(POWER_ID,NAME,PowerType.BUFF,true,owner,amount);
        isTwoAmount = true;
        this.amount2 += damage;
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer){
            Wiz.atb(new ScryAction(amount));
            Wiz.doDmg(AbstractDungeon.player, amount2);
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount2 + DESCRIPTIONS[2];
    }
}
