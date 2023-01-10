package thePackmaster.powers.psychicpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.psychicpack.LockingCardInterface;
import thePackmaster.patches.psychicpack.occult.OccultPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class GravityPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GravityPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public GravityPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }


    @Override
    public void onCardDraw(AbstractCard c) {
        if ((c instanceof LockingCardInterface && ((LockingCardInterface) c).isLocked()) ||
                OccultPatch.isUnplayable(AbstractDungeon.player, c)) {
            this.flash();
            addToTop(new DrawCardAction(this.amount));
        }
    }

    @Override
    public void updateDescription() {
        if (this.amount == 1)
        {
            this.description = DESCRIPTIONS[0];
        }
        else
        {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }
}
