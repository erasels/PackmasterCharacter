package thePackmaster.powers.bellordpack;


import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LikeClockworkPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("LikeClockworkPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LikeClockworkPower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, amount);
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        for (AbstractCard q : AbstractDungeon.player.hand.group) {
            if (q.type == AbstractCard.CardType.CURSE || q.type == AbstractCard.CardType.STATUS || q.color == AbstractCard.CardColor.CURSE) {
                flash();
                addToBot(new GainBlockAction(owner, amount));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
