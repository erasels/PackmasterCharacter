package thePackmaster.powers.darksoulspack;

import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
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

    private boolean apply = false;


    public ReplenishmentPower(AbstractCreature owner, int amount){
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }

    public void atEndOfTurn(boolean isPlayer) {
        if (apply) {
            Wiz.atb(new AddTemporaryHPAction(owner, owner, amount));
            apply = false;
        }
    }

    //should trigger on temp HP loss but DOESNT
    @Override
    public void wasHPLost(DamageInfo info, int damageAmount) {
        if (!apply && info.owner == owner) {
            this.flash();
            apply = true;
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
