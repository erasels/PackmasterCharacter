package thePackmaster.powers.artificerpack;

import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.HandSelectAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BattleFocusPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("BattleFocusPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final UIStrings strings = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("LimitedGambleAction"));

    public BattleFocusPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new HandSelectAction(amount, (c)->true, (list) -> {},
                (list) -> {
                    for (AbstractCard c : list) {
                        AbstractDungeon.handCardSelectScreen.selectedCards.moveToDiscardPile(c);
                        c.triggerOnManualDiscard();
                        GameActionManager.incrementDiscard(false);
                        addToBot(new DrawCardAction(1));
                    }
                }, strings.TEXT[0], false, true, true ));
    }

    @Override
    public void updateDescription() {
        description = (amount == 1 ? DESCRIPTIONS[0] : DESCRIPTIONS[1] + amount + DESCRIPTIONS[2]);
    }
}
