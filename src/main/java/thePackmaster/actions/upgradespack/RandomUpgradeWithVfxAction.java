package thePackmaster.actions.upgradespack;

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

        //Skip vfx and waiting for 10+ amount
        while (amount > 10) {
            CardGroup upgradableCards = group.getUpgradableCards();
            if (upgradableCards.size() > 0) {
                int rIndex = AbstractDungeon.cardRandomRng.random(upgradableCards.size() - 1);
                AbstractCard c = upgradableCards.group.get(rIndex);
                c.upgrade();
                amount--;
            } else {
                break;
            }
        }


        if (duration == Settings.ACTION_DUR_XFAST) {
            CardGroup upgradables = group.getUpgradableCards();
            if (upgradables.size() >= 1) {
                int r = AbstractDungeon.cardRandomRng.random(upgradables.size() -1);
                AbstractCard c = upgradables.group.get(r);
                c.upgrade();
                AbstractDungeon.topLevelEffects.add(new LightUpgradeShineEffect(c.current_x, c.current_y));
                if (amount > 1) {
                    addToBot(new RandomUpgradeWithVfxAction(amount - 1, group));
                }
            }
        }
        tickDuration();
    }

}
