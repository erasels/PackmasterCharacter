package thePackmaster.actions.quietpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.util.Wiz;

import java.util.stream.Collectors;

import static thePackmaster.util.Wiz.adp;

public class ShineAction extends AbstractGameAction {
    AbstractCard originalCard;
    boolean upgraded;

    public ShineAction(AbstractCard card, boolean up) {
        originalCard = card;
        upgraded = up;
    }

    public void update() {
        if (adp().hand.group.size() < 2) {
            isDone = true;
            return;
        }

        if (upgraded) {
            for (AbstractCard c : adp().hand.group) {
                if (c.canUpgrade()) {
                    c.upgrade();
                    c.superFlash();
                    c.applyPowers();
                }
            }
        } else {
            AbstractCard c = Wiz.getRandomItem(adp().hand.group.stream()
                    .filter(card -> card != originalCard)
                    .collect(Collectors.toList()));

            if (c.canUpgrade()) {
                c.upgrade();
                c.superFlash();
                c.applyPowers();
            }
        }

        isDone = true;
    }
}