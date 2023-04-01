package thePackmaster.powers.fueledpack;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.adp;

public class BurnBrightPower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(BurnBrightPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public BurnBrightPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void updateDescription() {
        if (amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.POWER && amount > 0) {
            flash();
            AbstractMonster m = null;
            if (action.target != null)
                m = (AbstractMonster)action.target;

            AbstractCard tmp = card.makeSameInstanceOf();
            adp().limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float)Settings.HEIGHT / 2.0F;
            if (m != null)
                tmp.calculateCardDamage(m);

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(
                    tmp, m, card.energyOnUse, true, true), true);
            --amount;
            if (amount == 0)
                addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        }
    }
}