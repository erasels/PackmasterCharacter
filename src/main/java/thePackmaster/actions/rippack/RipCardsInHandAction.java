package thePackmaster.actions.rippack;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.rippack.ArtAttack;
import thePackmaster.cards.rippack.OnRipInterface;
import thePackmaster.patches.rippack.AllCardsRippablePatches;
import thePackmaster.vfx.rippack.ShowCardAndRipEffect;

import java.util.Iterator;
import java.util.List;

import static thePackmaster.util.Wiz.att;
import static thePackmaster.util.Wiz.isWholeCard;

public class RipCardsInHandAction extends AbstractGameAction {
    private List<AbstractCard> cardsToRip;
    private AbstractCard textCard;
    private AbstractCard artCard;

    public RipCardsInHandAction(List<AbstractCard> cardsToRip) {
        this.actionType = AbstractGameAction.ActionType.SPECIAL;
        this.duration = Settings.ACTION_DUR_MED;
        this.cardsToRip = cardsToRip;
    }

    @Override
    public void update() {
        Iterator<AbstractCard> cardsToRipIterator = cardsToRip.listIterator();
        while (cardsToRipIterator.hasNext()) {
            AbstractCard cardToRip = cardsToRipIterator.next();
            boolean found = false;
            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (card == cardToRip) {
                    found = true;
                    break;
                }
            }
            if (found && cardToRip != null && isWholeCard(cardToRip)) {
                float x = MathUtils.random(0.2F, 0.8F) * Settings.WIDTH;
                float y = MathUtils.random(0.3F, 0.7F) * Settings.HEIGHT;
                AbstractDungeon.effectsQueue.add(new ShowCardAndRipEffect(cardToRip.makeStatEquivalentCopy(), x, y));

                //Set up Art Half properties
                artCard = cardToRip.makeStatEquivalentCopy();
                artCard.rawDescription = "";
                artCard.initializeDescription();
                artCard.target = artCard.cardID.equals(ArtAttack.ID) ? artCard.target : AbstractCard.CardTarget.NONE;
                AllCardsRippablePatches.AbstractCardFields.ripStatus.set(artCard, AllCardsRippablePatches.RipStatus.ART);

                //Set up Text Half properties
                textCard = cardToRip.makeStatEquivalentCopy();
                textCard.cost = 0;
                textCard.costForTurn = 0;
                textCard.name = "";
                AllCardsRippablePatches.AbstractCardFields.ripStatus.set(textCard, AllCardsRippablePatches.RipStatus.TEXT);

                if (AbstractDungeon.player.hoveredCard == cardToRip) {
                    AbstractDungeon.player.releaseCard();
                }
                AbstractDungeon.actionManager.cardQueue.removeIf(q -> q.card == cardToRip);
                att(new MakeTempCardInHandAction(artCard));
                att(new MakeTempCardInHandAction(textCard));
                if (cardToRip instanceof OnRipInterface) {
                    ((OnRipInterface) cardToRip).onRip();
                }
                SpireAnniversary5Mod.cardsRippedThisTurn++;
                AbstractDungeon.player.hand.removeCard(cardToRip);
                AbstractDungeon.player.hand.applyPowers();
                AbstractDungeon.player.hand.glowCheck();
            }
            isDone = true;
        }
    }
}
