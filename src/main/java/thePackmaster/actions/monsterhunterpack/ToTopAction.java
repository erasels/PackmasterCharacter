//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package thePackmaster.actions.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ToTopAction extends AbstractGameAction {
    private AbstractPlayer p;
    private AbstractCard c;

    public ToTopAction(AbstractCard card) {
        this.p = AbstractDungeon.player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FASTER;
        c = card;
    }

    public void update() {
        this.p.hand.removeCard(c);
        this.p.hand.moveToDeck(c, false);
        this.isDone = true;
    }
}
