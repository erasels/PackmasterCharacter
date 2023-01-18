package thePackmaster.powers.evenoddpack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.AbstractPackmasterPower;

import java.util.Iterator;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GammaWardPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("GammaWardPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private int counter = 0;
    public boolean justRemoved;
    
    public GammaWardPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }
    
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    
    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        counter = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        justRemoved = false;
    }
    
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
        justRemoved = true;
    }
    
    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        counter++;
    }
    
    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(card.type == AbstractCard.CardType.SKILL)
        {
            if(counter % 2 == 1)
            {
                int tmp =  card.baseBlock;
                card.baseBlock = this.amount;
                card.applyPowers();
                int TotalBlock = card.block;
                card.baseBlock = tmp;
                card.applyPowers();
                addToBot(new GainBlockAction(owner, TotalBlock));
            }
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            justRemoved = true;
        }
    }
}
