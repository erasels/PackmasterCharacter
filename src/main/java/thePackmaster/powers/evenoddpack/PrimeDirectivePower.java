package thePackmaster.powers.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.util.dynamicdynamic.DynamicDynamicVariableManager;
import thePackmaster.util.dynamicdynamic.DynamicProvider;

import java.util.HashMap;
import java.util.UUID;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PrimeDirectivePower extends AbstractPackmasterPower implements DynamicProvider {
    public static final String POWER_ID = makeID("PrimeDirectivePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private final HashMap<AbstractCard, String> keyMap;
    private final UUID uuid;
    private int counter = 0;
    
    public PrimeDirectivePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
        uuid = UUID.randomUUID();
        keyMap = new HashMap<>();
    }
    
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    
    @Override
    public void onInitialApplication() {
        counter = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
    }
    
    @Override
    public void atStartOfTurn() {
       counter = 1;
    }
    
    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        counter++;
    }

    @Override
    public void onRemove() {
        super.onRemove();
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

    @Override
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if(type == DamageInfo.DamageType.NORMAL && counter % 2 == 1)
        {
            if (!keyMap.containsKey(card)) {
                keyMap.put(card, DynamicDynamicVariableManager.generateKey(card, this));
                DynamicDynamicVariableManager.registerVariable(card, this);
                card.initializeDescription();
            }
            return damage + amount;
        }
        if (keyMap.containsKey(card)) {
            keyMap.remove(card);
            card.initializeDescription();
        }
        return damage;
    }

    public String modifyDescription(String currentRaw, AbstractCard card) {
        if(card.type == AbstractCard.CardType.ATTACK && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0) {
            return currentRaw + DESCRIPTIONS[2] + "!" + keyMap.get(card) + "!" + DESCRIPTIONS[3];
        }
        return currentRaw;
    }

    @Override
    public UUID getDynamicUUID() {
        return uuid;
    }

    @Override
    public boolean isModified(AbstractCard card) {
        return false;
    }

    @Override
    public int value(AbstractCard card) {
        return amount;
    }

    @Override
    public int baseValue(AbstractCard card) {
        return amount;
    }
}
