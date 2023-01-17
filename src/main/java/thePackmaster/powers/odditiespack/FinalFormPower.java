package thePackmaster.powers.odditiespack;


import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.NonStackablePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.att;

public class FinalFormPower extends AbstractPackmasterPower implements NonStackablePower {
    public static final String POWER_ID = makeID("FinalFormPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private AbstractCard card;

    public FinalFormPower(AbstractCard held) {
        super(POWER_ID, NAME, PowerType.BUFF, false, AbstractDungeon.player, -1);
        card = held;
        canGoNegative = false;
        updateDescription();
    }

    @Override
    public void atStartOfTurnPostDraw() {
        flash();
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                AbstractCard q = card.makeStatEquivalentCopy();

                q.freeToPlayOnce = true;
                q.exhaust = true;

                att(new NewQueueCardAction(q, true, true, true));
            }
        });


    }

    @Override
    public void updateDescription() {
        if (card != null)
            description = DESCRIPTIONS[0] + FontHelper.colorString(card.name, "y") + DESCRIPTIONS[1];
        else
            description = "";
    }
}
