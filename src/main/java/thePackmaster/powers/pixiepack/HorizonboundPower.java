package thePackmaster.powers.pixiepack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.PixiePack;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.ArrayList;

public class HorizonboundPower extends AbstractPackmasterPower {
    public static final String POWER_ID = SpireAnniversary5Mod.makeID("HorizonboundPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private ArrayList<AbstractCard> alreadyApplied = new ArrayList<>();

    public HorizonboundPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF,false, owner, amount);
    }

    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        amount = 0;
        for (AbstractCard C:AbstractDungeon.actionManager.cardsPlayedThisTurn)
        {
            if (PixiePack.isForeign(C)) amount++;
            for (AbstractCard K:AbstractDungeon.player.hand.group) {
                if (PixiePack.isForeign(K) && K.cost >= 0)
                {
                    if(K.costForTurn <= amount) K.setCostForTurn(0);
                    else K.setCostForTurn(K.costForTurn - amount);
                }
            }
        }
    }

    @Override
    public void atStartOfTurn() {
        super.atStartOfTurnPostDraw();
        amount = 0;
        alreadyApplied.clear();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        super.onPlayCard(card, m);
        if (PixiePack.isForeign(card))
        {
            amount++;
            for (AbstractCard C:AbstractDungeon.player.hand.group) {
                if (PixiePack.isForeign(C) && C.cost >= 0)
                {
                    if (C.costForTurn>0) C.setCostForTurn(C.costForTurn - 1);
                }
            }
        }
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        super.onCardDraw(card);
        if (alreadyApplied.contains(card)) alreadyApplied.remove(card);
    }

    @Override
    public void update(int slot) {
        super.update(slot);
        for (AbstractCard C:AbstractDungeon.player.hand.group) {
            if (PixiePack.isForeign(C) && !alreadyApplied.contains(C) && C.cost >= 0) {
                alreadyApplied.add(C);
                if (C.costForTurn <= amount) C.setCostForTurn(0);
                else C.setCostForTurn(C.costForTurn - amount);
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
