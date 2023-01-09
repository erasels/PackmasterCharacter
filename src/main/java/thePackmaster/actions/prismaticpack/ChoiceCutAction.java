package thePackmaster.actions.prismaticpack;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.blue.DoomAndGloom;
import com.megacrit.cardcrawl.cards.green.Predator;
import com.megacrit.cardcrawl.cards.purple.ReachHeaven;
import com.megacrit.cardcrawl.cards.red.Uppercut;

import java.util.ArrayList;

public class ChoiceCutAction extends AbstractChooseOneCardAction {
    private boolean upgraded;

    public ChoiceCutAction(boolean upgraded) {
        super(1, true, 0);
        this.upgraded = upgraded;
    }

    @Override
    protected ArrayList<AbstractCard> generateCardChoices() {
        ArrayList<AbstractCard> cards = new ArrayList<>();
        cards.add(new Uppercut());
        cards.add(new DoomAndGloom());
        cards.add(new Predator());
        cards.add(new ReachHeaven());
        if (this.upgraded) {
            cards.forEach(AbstractCard::upgrade);
        }
        return cards;
    }
}
