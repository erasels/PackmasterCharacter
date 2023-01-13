package thePackmaster.powers.rimworldpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class TradeBeaconPower extends AbstractPackmasterPower implements DiscardHookPatch.OnDiscardThing{
    public static final String POWER_ID = makeID(TradeBeaconPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    boolean traded, postStartDraw;

    public TradeBeaconPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        traded = false;
        postStartDraw = true;
    }

    @Override
    public void atStartOfTurn() {
        traded = false;
        postStartDraw = false;
    }

    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToBot(new AbstractGameAction() {
                    @Override
                    public void update() {
                        if(adp() != null && adp().hasPower(TradeBeaconPower.POWER_ID))
                            ((TradeBeaconPower)adp().getPower(TradeBeaconPower.POWER_ID)).postStartDraw = true;
                        isDone = true;
                    }
                });
                isDone = true;
            }
        });
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        drawDiscardCheck();
    }

    @Override
    public void onManualDiscardThing() {
        drawDiscardCheck();
    }

    @Override
    public void updateDescription() {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }


    private void drawDiscardCheck() {
        if(postStartDraw && !traded && AbstractDungeon.overlayMenu.endTurnButton.enabled)
        {
            traded = true;
            flash();
            addToBot(new DrawCardAction(amount));
        }
    }
}
