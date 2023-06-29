package thePackmaster.powers.rimworldpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.rimworldpack.Despair;
import thePackmaster.cards.rimworldpack.FreeToPlayPower;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class CatharsisPower extends AbstractPackmasterPower implements FreeToPlayPower {
    public static final String POWER_ID = makeID(CatharsisPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public CatharsisPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0) {
            flash();
            AbstractMonster m = null;
            if (action.target != null)
                m = (AbstractMonster)action.target;
            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            if (m != null)
                tmp.calculateCardDamage(m);
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            if(amount > 1)
                addToTop(new ReducePowerAction(owner, owner, this, 1));
            else
                addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            updateDescription();
        }
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1]+ amount + DESCRIPTIONS[2];
    }
    @Override
    public void atStartOfTurn() {
        addToTop(new RemoveSpecificPowerAction(owner, owner, this));
    }

    @Override
    public boolean isFreeToPlay(AbstractCard card) {
        return true;
    }

}
