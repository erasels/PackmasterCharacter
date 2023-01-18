package thePackmaster.cardmodifiers.evenoddpack;

import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import thePackmaster.powers.evenoddpack.PrimeDirectivePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AuraTextModifier extends AbstractCardModifier {
    public final static String ID = makeID("EvenOddAppend");
    public final static CardStrings cardstrings  = CardCrawlGame.languagePack.getCardStrings(ID);
    
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
                        return rawDescription + cardstrings.EXTENDED_DESCRIPTION[0]
                                + AbstractDungeon.player.getPower(PrimeDirectivePower.POWER_ID).amount
                                + cardstrings.EXTENDED_DESCRIPTION[1];
        }
        else
        {
            return rawDescription;
        }
    }
    
    @Override
    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, identifier(card));
    }
    
    @Override
    public String identifier(AbstractCard card) {
        return makeID("AuraTextModifier");
    }
    
    @Override
    public void onApplyPowers(AbstractCard card) {
        super.onApplyPowers(card);
        if(AbstractDungeon.player != null
                && card.type == AbstractCard.CardType.ATTACK
                && AbstractDungeon.player.hasPower(PrimeDirectivePower.POWER_ID)
                && AbstractDungeon.player.hand.contains(card))
        {
            card.initializeDescription();
        }
    }
}
