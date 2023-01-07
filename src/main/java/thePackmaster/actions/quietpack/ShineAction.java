package thePackmaster.actions.quietpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static thePackmaster.util.Wiz.adp;

public class ShineAction extends AbstractGameAction {
    AbstractCard originalCard;
    boolean upgraded;

    public ShineAction(AbstractCard card, boolean up) {
        originalCard = card;
        upgraded = up;
    }

    public void update() {
        if (adp().hand.group.size() < 2)
            return;

        if (upgraded) {
            for (AbstractCard c : adp().hand.group) {
                if (c.canUpgrade()) {
                    c.upgrade();
                    c.superFlash();
                    c.applyPowers();
                }
            }
        } else {
            AbstractCard c = randomOtherCard();

            if (c.canUpgrade()) {
                c.upgrade();
                c.superFlash();
                c.applyPowers();
            }
        }

        isDone = true;
    }

    private AbstractCard randomOtherCard() {
        AbstractCard c = adp().hand.getRandomCard(AbstractDungeon.cardRandomRng);
        if (c == originalCard) {
           return randomOtherCard();
        }
        return c;
    }

}
