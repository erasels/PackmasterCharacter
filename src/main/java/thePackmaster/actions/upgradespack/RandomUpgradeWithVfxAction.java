package thePackmaster.actions.upgradespack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.vfx.upgradespack.LightUpgradeShineEffect;

public class RandomUpgradeWithVfxAction extends AbstractGameAction {

    private final CardGroup group;

    public RandomUpgradeWithVfxAction(int amount, CardGroup group) {
        this.group = group;
        this.amount = amount;
        duration = Settings.ACTION_DUR_XFAST;
    }

    @Override
    public void update() {
        if (duration == Settings.ACTION_DUR_XFAST) {
            CardGroup upgradables = group.getUpgradableCards();
            if (upgradables.size() >= 1) {
                AbstractCard c = upgradables.getRandomCard(true);
                //if (group.type == CardGroup.CardGroupType.HAND) {
                    AbstractDungeon.topLevelEffects.add(new LightUpgradeShineEffect(c.current_x, c.current_y));
                //}
                c.upgrade();
                BaseMod.logger.info("Upgraded " + c);
                if (amount > 1) {
                    BaseMod.logger.info("Added action with amount " + (amount - 1));
                    addToBot(new RandomUpgradeWithVfxAction(amount - 1, group));
                }
            }
        }
        tickDuration();
    }

}
