package thePackmaster.actions.rippack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.rippack.AbstractRippedArtCard;

import java.util.stream.Collectors;

public class ExhaustRandomNonArtCardsAction extends AbstractGameAction {

    private int amount;

    public ExhaustRandomNonArtCardsAction(int amount) {
        this.amount = amount;
    }

    @Override
    public void update() {
        int validCardsCount = AbstractDungeon.player.hand.group.stream().filter(card -> !(card instanceof AbstractRippedArtCard)).collect(Collectors.toList()).size();
        amount = validCardsCount < amount ? validCardsCount : amount; //Set the amount to exhaust to the amount of valid cards if it's less than requested
        for (int i = 0; i < this.amount; i++) {
            AbstractCard card = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
            while(card instanceof AbstractRippedArtCard) {
                card = AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng);
            }
            AbstractDungeon.player.hand.moveToExhaustPile(AbstractDungeon.player.hand.getRandomCard(AbstractDungeon.cardRandomRng));
        }
        isDone = true;
    }
}
