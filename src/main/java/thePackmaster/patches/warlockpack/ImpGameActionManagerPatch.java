package thePackmaster.patches.warlockpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.cards.warlockpack.Imp;

// HandCardSelectScreen calls GameActionManager.cleanCardQueue, and is one of only two places that calls that method, the
// other being AbstractMonster.die(). If you draw imps at the same time as a card that opens HandCardSelectScreen is played,
// this means that the card queue item for playing the imps vanishes. To fix this, we add them back in an action that
// executes afterwards.
@SpirePatch(clz = GameActionManager.class, method = "cleanCardQueue", paramtypez = {})
public class ImpGameActionManagerPatch {
    @SpirePostfixPatch
    public static void addImpsBackToQueue() {
        if (AbstractDungeon.currMapNode != null && AbstractDungeon.currMapNode.room != null && AbstractDungeon.currMapNode.room.monsters != null && !AbstractDungeon.currMapNode.room.monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractCard c : AbstractDungeon.player.hand.group) {
                        if (c instanceof Imp) {
                            ((Imp)c).triggerWhenAddedToHand();
                        }
                    }
                    this.isDone = true;
                }
            });
        }
    }
}
