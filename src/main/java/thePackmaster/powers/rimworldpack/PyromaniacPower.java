package thePackmaster.powers.rimworldpack;

import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.rimworldpack.OnFireModifier;
import thePackmaster.patches.DiscardHookPatch;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.adp;

public class PyromaniacPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(PyromaniacPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;

    boolean traded, postStartDraw;

    public PyromaniacPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        traded = false;
        postStartDraw = true;
    }

    //Set a random card in your hand on Fire
    @Override
    public void atStartOfTurnPostDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                if(AbstractDungeon.player.hand.size() == 0)
                {
                    isDone = true;
                    return;
                }
                AbstractCard kindle = AbstractDungeon.player.hand.getRandomCard(true);
                if(kindle == null)
                {
                    isDone = true;
                    return;
                }
                CardModifierManager.addModifier(kindle, new OnFireModifier());
                kindle.superFlash(Color.ORANGE.cpy());
                isDone = true;
            }
        });
        flash();
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
