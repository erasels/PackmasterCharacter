package thePackmaster.powers.evenoddpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.evenoddpack.AbstractEvenOddCard;
import thePackmaster.cards.evenoddpack.GammaWardDummy;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.dynamicdynamic.DynamicDynamicVariableManager;
import thePackmaster.util.dynamicdynamic.DynamicProvider;

import java.util.HashMap;
import java.util.UUID;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GammaWardPower extends AbstractPackmasterPower implements DynamicProvider {
    public static final String POWER_ID = makeID("GammaWardPower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private final HashMap<AbstractCard, String> keyMap;
    private final HashMap<AbstractCard, Integer> calculationMap;
    private final AbstractCard dummy = new GammaWardDummy();
    private int counter = 0;
    private boolean preventLoop = false;
    
    public GammaWardPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
        keyMap = new HashMap<>();
        calculationMap = new HashMap<>();
    }
    
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    
    @Override
    public void onInitialApplication() {
        counter = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
    }
    
    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
    }
    
    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        counter++;
    }

    @Override
    public float modifyBlock(float blockAmount, AbstractCard card) {
        dummy.baseBlock = amount;
        if (!preventLoop) {
            preventLoop = true;
            CardModifierManager.copyModifiers(card, dummy, true, true, false);
            dummy.applyPowers();
            calculationMap.put(card, dummy.block);
            preventLoop = false;
        }
        if (counter % 2 == 1 && !keyMap.containsKey(card)) {
            DynamicDynamicVariableManager.registerVariable(card, this);
            keyMap.put(card, DynamicDynamicVariableManager.generateKey(card, this));
            card.initializeDescription();
        } else if (counter % 2 == 0 && keyMap.containsKey(card)) {
            card.initializeDescription();
        }
        return super.modifyBlock(blockAmount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if(card.type == AbstractCard.CardType.SKILL)
        {
            if(counter % 2 == 1)
            {
                dummy.baseBlock = amount;
                CardModifierManager.copyModifiers(card, dummy, true, true, false);
                preventLoop = true;
                dummy.applyPowers();
                preventLoop = false;
                addToBot(new GainBlockAction(owner, dummy.block));
            }
            addToBot(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            addToBot(new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractCard card : keyMap.keySet()) {
                        card.initializeDescription();
                    }
                    isDone = true;
                }
            });
        }
    }
    
    @Override
    public void onCardDraw(AbstractCard card) {
        card.initializeDescription();
    }

    public String modifyDescription(String currentRaw, AbstractCard card) {
        if (card.type == AbstractCard.CardType.SKILL
                && AbstractDungeon.player.hand.contains(card)) {
            if(AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1) {
                return currentRaw +  DESCRIPTIONS[2] + DESCRIPTIONS[3] + " !" + keyMap.get(card) + "!" + DESCRIPTIONS[4];
            }
            else
            {
                return currentRaw + DESCRIPTIONS[2] + AbstractEvenOddCard.makeCardTextGray( DESCRIPTIONS[3]) + " !" + keyMap.get(card) + "!" + AbstractEvenOddCard.makeCardTextGray( DESCRIPTIONS[4]);
            }
        }
        return currentRaw;
    }

    @Override
    public UUID getDynamicUUID() {
        return dummy.uuid;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return calculationMap.get(card) != dummy.baseBlock;
    }

    @Override
    public int value(AbstractCard card) {
        return calculationMap.get(card);
    }

    @Override
    public int baseValue(AbstractCard card) {
        return dummy.baseBlock;
    }
}
