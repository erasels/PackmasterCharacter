package thePackmaster.powers.summonerspellspack;

import com.megacrit.cardcrawl.actions.utility.DiscardToHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.p;

public class TeleportBotPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("TeleportBotPower");
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    public TeleportBotPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
    }

    @Override
    public void atStartOfTurnPostDraw() {
        boolean flashed = false;

        if (!p().discardPile.isEmpty()) {
            for (int i = 0; i < this.amount; i++) {
                if (i < p().discardPile.group.size()) {
                    AbstractCard c = p().discardPile.group.get(i);
                    addToBot(new DiscardToHandAction(c));
                    if (!flashed) {
                        flash();
                        flashed = true;
                    }
                }
            }
        }
    }

    public void stackPower(int stackAmount) {
        this.fontScale = 8.0F;
        this.amount += stackAmount;
    }

    public void updateDescription() {
        this.description = this.amount == 1 ?
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] :
                DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    }

}
