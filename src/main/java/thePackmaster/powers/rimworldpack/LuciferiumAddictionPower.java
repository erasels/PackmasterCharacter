package thePackmaster.powers.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.StrengthPower;
import thePackmaster.cards.rimworldpack.Despair;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class LuciferiumAddictionPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(LuciferiumAddictionPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public static int DESPAIR = 2, STRENGTH = 5;

    public LuciferiumAddictionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.DEBUFF, false, owner, amount);
        isTurnBased = true;
    }

    @Override
    public void atStartOfTurn() {
        amount--;
        if(amount <= 0)
        {
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            addToBot(new MakeTempCardInHandAction(new Despair(), DESPAIR));
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, -STRENGTH), -STRENGTH));
        }

        updateDescription();
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

}
