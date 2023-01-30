package thePackmaster.powers.basicspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.actions.upgradespack.SuperUpgradeAction;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PerfectBasicPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(PerfectBasicPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public PerfectBasicPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        ArrayList<AbstractCard> basics = new ArrayList<>();
        if (c.rarity == AbstractCard.CardRarity.BASIC) {
            flash();
            basics.add(c);
        }
        addToBot(new SuperUpgradeAction(basics, this.amount));
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }

}
