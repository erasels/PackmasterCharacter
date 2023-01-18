package thePackmaster.actions.creativitypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import thePackmaster.actions.EasyModalChoiceAction;
import thePackmaster.cards.creativitypack.special.BrushModalTypeChoice;

import java.util.ArrayList;

public class PaintBrushAction extends AbstractGameAction
{
    boolean upgradeCards;
    public PaintBrushAction(boolean upgraded)
    {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
        upgradeCards = upgraded;
    }
    @Override
    public void update() {
        ArrayList<AbstractCard> list = new ArrayList<>();
        list.add(new BrushModalTypeChoice(AbstractCard.CardType.ATTACK));
        list.add(new BrushModalTypeChoice(AbstractCard.CardType.SKILL));
        list.add(new BrushModalTypeChoice(AbstractCard.CardType.POWER));
        if (upgradeCards) list.forEach(AbstractCard::upgrade);
        addToBot(new EasyModalChoiceAction(list));
        isDone = true;
    }
}
