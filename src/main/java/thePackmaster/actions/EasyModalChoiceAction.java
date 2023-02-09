package thePackmaster.actions;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsCenteredAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class EasyModalChoiceAction extends SelectCardsCenteredAction {

    public EasyModalChoiceAction(ArrayList<AbstractCard> list, int amount, String textforSelect) {
        super(list, amount, textforSelect, (cards) -> {
            for (AbstractCard q : cards) {
                q.onChoseThisOption();
            }
        });
    }

    public EasyModalChoiceAction(ArrayList<AbstractCard> list, int amount) {
        this(list, amount, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[1]);
    }

    public EasyModalChoiceAction(ArrayList<AbstractCard> list) {
        this(list, 1, CardCrawlGame.languagePack.getUIString(makeID("ModalChoice")).TEXT[1]);
    }
}
