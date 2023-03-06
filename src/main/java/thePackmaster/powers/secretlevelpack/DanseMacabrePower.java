package thePackmaster.powers.secretlevelpack;


import com.megacrit.cardcrawl.actions.animations.AnimateHopAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DanseMacabrePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("DanseMacabrePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public DanseMacabrePower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (Wiz.getEnemies().stream().anyMatch(q -> q.hasPower(WeakPower.POWER_ID) || q.hasPower(VulnerablePower.POWER_ID))) {
            flash();
        }
        Wiz.forAllMonstersLiving(q -> {
            if (q.hasPower(WeakPower.POWER_ID) || q.hasPower(VulnerablePower.POWER_ID)) {
                addToBot(new AnimateHopAction(q));
                addToBot(new LoseHPAction(q, owner, amount));
            }
        });
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
