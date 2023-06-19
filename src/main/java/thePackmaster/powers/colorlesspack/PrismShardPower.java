package thePackmaster.powers.colorlesspack;


import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.cards.colorlesspack.ThePrism;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PrismShardPower extends AbstractPackmasterPower implements OnReceivePowerPower {
    public static final String POWER_ID = makeID("PrismShardPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private boolean upgraded;
    private static final int NUM_NEEDED = 3;

    public PrismShardPower(boolean upgraded) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, 1);
        this.upgraded = upgraded;
        updateDescription();
    }

    public void playApplyPowerSfx() {
        CardCrawlGame.sound.play("POWER_MANTRA", 0.05F);
    }

    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        if (this.amount >= NUM_NEEDED) {
            AbstractCard q = new ThePrism();
            if (upgraded) q.upgrade();
            addToTop(new MakeTempCardInHandAction(q));
            this.amount -= NUM_NEEDED;
            if (this.amount <= 0) {
                this.addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
    }

    @Override
    public void updateDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(DESCRIPTIONS[0]);
        sb.append(FontHelper.colorString(CardLibrary.getCard(ThePrism.ID).originalName, "y"));
        if (upgraded) sb.append(DESCRIPTIONS[1]);
        sb.append(DESCRIPTIONS[2]);
        description = sb.toString();
    }

    @Override
    public boolean onReceivePower(AbstractPower p, AbstractCreature abstractCreature, AbstractCreature abstractCreature1) {
        if(!upgraded && p instanceof PrismShardPower && ((PrismShardPower) p).upgraded) {
            upgraded = true;
            updateDescription();
        }
        return true;
    }

    @Override
    public int onReceivePowerStacks(AbstractPower p, AbstractCreature target, AbstractCreature source, int stackAmount) {
        if(!upgraded && p instanceof PrismShardPower && ((PrismShardPower) p).upgraded) {
            upgraded = true;
            updateDescription();
        }
        return stackAmount;
    }
}
