package thePackmaster.powers.odditiespack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AutoBattlerPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("AutoBattlerPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AutoBattlerPower() {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, -1);
        canGoNegative = false;
    }

    @Override
    public void onInitialApplication() {
        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                Wiz.p().hand.refreshHandLayout();
                isDone = true;
            }
        });
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
    }
}
