package thePackmaster.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ChangePlayedCardExhaustAction extends AbstractGameAction {
    private final AbstractCard card;
    private final boolean exhaust;

    public ChangePlayedCardExhaustAction(AbstractCard card, boolean exhaust) {
        this.card = card;
        this.exhaust = exhaust;
    }

    @Override
    public void update() {
        for (AbstractGameAction a : AbstractDungeon.actionManager.actions) {
            if (a instanceof UseCardAction) {
                if (ReflectionHacks.getPrivate(a, UseCardAction.class, "targetCard") == card) {
                    ((UseCardAction) a).exhaustCard = this.exhaust;
                }
            }
        }
        this.isDone = true;
    }
}
