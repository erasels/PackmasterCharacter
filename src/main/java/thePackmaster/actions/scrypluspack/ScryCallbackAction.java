package thePackmaster.actions.scrypluspack;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.function.Consumer;

public class ScryCallbackAction
        extends ScryAction
{
    public Consumer<ArrayList<AbstractCard>> callback;

    public ScryCallbackAction(int numCards, Consumer<ArrayList<AbstractCard>> callback)
    {
        super(numCards);
        this.callback = callback;
    }
}