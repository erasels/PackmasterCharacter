package thePackmaster.actions.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.rippack.ArtAttack;
import thePackmaster.cards.rippack.OnRipInterface;
import thePackmaster.patches.rippack.AllCardsRippablePatches;

import static thePackmaster.util.Wiz.att;

public class RipCardAction extends AbstractGameAction {
    private AbstractCard rippedCard;
    private AbstractCard textCard;
    private AbstractCard artCard;

    public RipCardAction(AbstractCard rippedCard) {
        this.actionType = ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_MED;
        this.rippedCard = rippedCard;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        boolean found = false;
        for (AbstractCard card : p.hand.group) {
            if (card == rippedCard) {
                found = true;
                break;
            }
        }
        if(found && rippedCard != null) {
            //Set up Art Half properties
            artCard = rippedCard.makeStatEquivalentCopy();
            artCard.rawDescription = "";
            artCard.initializeDescription();
            artCard.target = artCard.cardID.equals(ArtAttack.ID) ? artCard.target : AbstractCard.CardTarget.NONE;
            artCard.cost = rippedCard.cost;
            artCard.costForTurn = rippedCard.costForTurn;
            AllCardsRippablePatches.AbstractCardFields.ripStatus.set(artCard, AllCardsRippablePatches.RipStatus.ART);

            //Set up Text Half properties
            textCard = rippedCard.makeStatEquivalentCopy();
            textCard.cost = 0;
            textCard.costForTurn = 0;
            textCard.name = "";
            AllCardsRippablePatches.AbstractCardFields.ripStatus.set(textCard, AllCardsRippablePatches.RipStatus.TEXT);

            if (AbstractDungeon.player.hoveredCard == rippedCard) {
                AbstractDungeon.player.releaseCard();
            }
            AbstractDungeon.actionManager.cardQueue.removeIf(q -> q.card == rippedCard);
            att(new MakeTempCardInHandAction(artCard));
            att(new MakeTempCardInHandAction(textCard));
            if(rippedCard instanceof OnRipInterface) {
                ((OnRipInterface)rippedCard).onRip();
            }
            SpireAnniversary5Mod.cardsRippedThisTurn++;
            AbstractDungeon.player.hand.removeCard(rippedCard);
            p.hand.applyPowers();
            p.hand.glowCheck();
        }
        isDone = true;
    }

}
