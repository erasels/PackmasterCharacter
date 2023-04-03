package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;

import static thePackmaster.util.Wiz.adp;

public class PlayOldPowerAction extends AbstractGameAction {
    private static final String UI_KEY = SpireAnniversary5Mod.makeID("BurnBrightUiText");
    private static final UIStrings uiStrings;
    public static final String[] TEXT;

    public PlayOldPowerAction() {
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.SPECIAL;
    }

    public void update() {
        if (duration == startDuration) {
            CardGroup powerGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
            powerGroup.group.addAll(SpireAnniversary5Mod.powerList);

            if (powerGroup.size() == 0) {
                isDone = true;
                return;
            }

            if (powerGroup.size() == 1) {
                isDone = true;
                finish(powerGroup.group.get(0));
                return;
            }

            AbstractDungeon.gridSelectScreen.open(powerGroup, 1, false, TEXT[0]);
            tickDuration();
            return;
        }

        if (!AbstractDungeon.gridSelectScreen.selectedCards.isEmpty()) {
            finish(AbstractDungeon.gridSelectScreen.selectedCards.get(0));
            AbstractDungeon.gridSelectScreen.selectedCards.clear();
        }

        tickDuration();
    }

    public void finish(AbstractCard card) {
        AbstractCard tmp = card.makeSameInstanceOf();
        adp().limbo.addToBottom(tmp);
        tmp.current_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.current_y = (float)Settings.HEIGHT / 2.0F;
        tmp.target_x = (float)Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
        tmp.target_y = (float)Settings.HEIGHT / 2.0F;

        tmp.purgeOnUse = true;
        AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(
                tmp, null, card.energyOnUse, true, true), true);
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString(UI_KEY);
        TEXT = uiStrings.TEXT;
    }
}