package thePackmaster.cardmodifiers.transmutationpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.dynamicdynamic.DynamicDynamicVariableManager;
import thePackmaster.util.dynamicdynamic.DynamicProvider;

import java.util.UUID;

@AbstractCardModifier.SaveIgnore
public abstract class AbstractExtraEffectModifier extends AbstractCardModifier implements DynamicProvider {
    private static final Color normalColor = Color.valueOf("65ada1");
    private static final Color increasedValueColor = Color.valueOf("9ad6b6");
    private static final Color decreasedValueColor = Color.valueOf("b799bd");
    protected static final String ID = SpireAnniversary5Mod.makeID("AbstractExtraEffectModifier");
    protected static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    protected static final String[] TEXT = cardStrings.EXTENDED_DESCRIPTION;
    private final VariableType type;
    public AbstractCard attachedCard;
    public boolean isValueModified;
    public int value;
    public int baseValue;
    protected String key;
    public boolean isMutable;
    protected int amount;

    public AbstractExtraEffectModifier(AbstractCard card, VariableType type, boolean isMutable, int times) {
        attachedCard = card.makeStatEquivalentCopy();
        this.type = type;
        this.isMutable = isMutable;
        amount = times;
        setValues();
    }

    public boolean isModified(AbstractCard card) {
        return isValueModified;
    }

    public int value(AbstractCard card) {
        return value;
    }

    public int baseValue(AbstractCard card) {
        return baseValue;
    }

    @Override
    public void onApplyPowers(AbstractCard card) {
        CardModifierManager.removeAllModifiers(attachedCard, true);
        for (AbstractCardModifier mod : CardModifierManager.modifiers(card)) {
            if (!(mod instanceof AbstractExtraEffectModifier)) {
                CardModifierManager.addModifier(attachedCard, mod.makeCopy());
            }
        }
        attachedCard.applyPowers();
        setValues();
    }

    @Override
    public void onCalculateCardDamage(AbstractCard card, AbstractMonster mo) {
        CardModifierManager.removeAllModifiers(attachedCard, true);
        for (AbstractCardModifier mod : CardModifierManager.modifiers(card)) {
            if (!(mod instanceof AbstractExtraEffectModifier)) {
                CardModifierManager.addModifier(attachedCard, mod.makeCopy());
            }
        }
        attachedCard.calculateCardDamage(mo);
        setValues();
    }

    protected void setValues() {
        switch(type) {
            case DAMAGE:
                value = attachedCard.damage;
                baseValue = attachedCard.baseDamage;
                isValueModified = attachedCard.isDamageModified;
                break;
            case BLOCK:
                value = attachedCard.block;
                baseValue = attachedCard.baseBlock;
                isValueModified = attachedCard.isBlockModified;
                break;
            case MAGIC:
                value = attachedCard.magicNumber;
                baseValue = attachedCard.baseMagicNumber;
                isValueModified = attachedCard.isMagicNumberModified;
                break;
        }
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        doExtraEffects(card, AbstractDungeon.player, target);
    }

    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        return addExtraText(rawDescription, card);
    }

    public abstract void doExtraEffects(AbstractCard card, AbstractPlayer p, AbstractCreature m);

    public abstract String addExtraText(String rawDescription, AbstractCard card);


    @Override
    public Color getNormalColor() {
        return normalColor;
    }

    @Override
    public Color getIncreasedValueColor() {
        return increasedValueColor;
    }

    @Override
    public Color getDecreasedValueColor() {
        return decreasedValueColor;
    }

    @Override
    public UUID getDynamicUUID() {
        return attachedCard.uuid;
    }

    @Override
    public void onInitialApplication(AbstractCard card) {
        DynamicDynamicVariableManager.registerVariable(card, this);
        key = "!" + DynamicDynamicVariableManager.generateKey(card, this) + "!";
    }

    protected enum VariableType {
        DAMAGE,
        BLOCK,
        MAGIC
    }

    public void onCardTransmuted(AbstractCard card, AbstractCard newCard) {

    }

    protected void addToTop(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToTop(action);
    }

    protected void addToBot(AbstractGameAction action) {
        AbstractDungeon.actionManager.addToBottom(action);
    }

    protected String applyTimes(String s) {
        if (amount == 1) {
            s += TEXT[3];
        } else {
            s += TEXT[1] + amount + TEXT[2];
        }
        return s;
    }

    protected String applyMutable(String s) {
        if (isMutable) {
            s = TEXT[0] + s;
        }
        return s;
    }
}
