package thePackmaster.powers.evenoddpack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cardmodifiers.evenoddpack.AuraTextModifier;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.entropypack.EntropyPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PrimeDirectivePower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID("PrimeDirectivePower");
    private static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    private static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private int counter = 0;
    
    public PrimeDirectivePower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, owner, amount);
        updateDescription();
    }
    
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
    
    @Override
    public void onInitialApplication() {
        super.onInitialApplication();
        for (AbstractCard card:AbstractDungeon.player.hand.group) {
            CardModifierManager.addModifier(card, new AuraTextModifier());
        }
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
    public float atDamageGive(float damage, DamageInfo.DamageType type, AbstractCard card) {
        if(type == DamageInfo.DamageType.NORMAL && counter  % 2 == 1)
        {
            return damage + amount;
        }
        return damage;
    }
}
