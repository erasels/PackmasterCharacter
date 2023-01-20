package thePackmaster.cardmodifiers.evenoddpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.powers.evenoddpack.GammaWardPower;
import thePackmaster.powers.evenoddpack.PrimeDirectivePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AuraTextModifier extends AbstractCardModifier {
    public final static String EVEN_ODD_APPEND_ID = makeID("EvenOddAppend");
    public final static CardStrings cardstrings  = CardCrawlGame.languagePack.getCardStrings(EVEN_ODD_APPEND_ID);
    public final static String MODIFIER_ID = makeID("AuraTextModifier");
    public boolean hasAuraText = false;
    private static boolean lock = false;
    
    @Override
    public AbstractCardModifier makeCopy() {
        return new AuraTextModifier();
    }
    
    @Override
    public String modifyDescription(String rawDescription, AbstractCard card) {
        if(AbstractDungeon.player != null
                && card.type == AbstractCard.CardType.ATTACK
                && AbstractDungeon.player.hasPower(PrimeDirectivePower.POWER_ID)
                && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0
                && AbstractDungeon.player.hand.contains(card))
        {
            hasAuraText = true;
            return rawDescription + cardstrings.EXTENDED_DESCRIPTION[0]
                    + AbstractDungeon.player.getPower(PrimeDirectivePower.POWER_ID).amount
                    + cardstrings.EXTENDED_DESCRIPTION[1];
        }
        else if(AbstractDungeon.player != null
                && card.type == AbstractCard.CardType.SKILL
                && AbstractDungeon.player.hasPower(GammaWardPower.POWER_ID)
                && AbstractDungeon.player.hand.contains(card)
                && AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1
                && !((GammaWardPower)AbstractDungeon.player.getPower(GammaWardPower.POWER_ID)).justRemoved)
        {
            hasAuraText = true;
            int tmp =  card.baseBlock;
            card.baseBlock = AbstractDungeon.player.getPower(GammaWardPower.POWER_ID).amount;
            lock = true;
            card.applyPowers();
            int TotalBlock = card.block;
            card.baseBlock = tmp;
            card.applyPowers();
            lock = false;
            return rawDescription + cardstrings.EXTENDED_DESCRIPTION[2]
                    + TotalBlock
                    + cardstrings.EXTENDED_DESCRIPTION[3];
        }
        else
        {
            hasAuraText = false;
            return rawDescription;
        }
    }
    
    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, identifier(card));
    }
    
    @Override
    public String identifier(AbstractCard card) {
        return MODIFIER_ID;
    }
    
    @Override
    public void onApplyPowers(AbstractCard card) {
        super.onApplyPowers(card);
        if(AbstractDungeon.player != null
                && (card.type == AbstractCard.CardType.ATTACK
                && AbstractDungeon.player.hasPower(PrimeDirectivePower.POWER_ID)
                && AbstractDungeon.player.hand.contains(card)
                || card.type == AbstractCard.CardType.SKILL
                && AbstractDungeon.player.hasPower(GammaWardPower.POWER_ID)
                && AbstractDungeon.player.hand.contains(card))
                && !lock)
        {
            card.initializeDescription();
        }
    }
}
