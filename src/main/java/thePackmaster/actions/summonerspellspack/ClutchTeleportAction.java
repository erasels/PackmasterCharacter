package thePackmaster.actions.summonerspellspack;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import thePackmaster.powers.summonerspellspack.WitheringExhaustPower;
import thePackmaster.util.Wiz;

public class ClutchTeleportAction extends AbstractGameAction {

    public ClutchTeleportAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    public void update() {
        boolean handWasFull = false;
        if (!Wiz.drawPile().isEmpty()) {
            AbstractCard c = Wiz.drawPile().getRandomCard(true);
            if (Wiz.hand().size() < BaseMod.MAX_HAND_SIZE)
                Wiz.drawPile().moveToHand(c);
            else
            {
                Wiz.drawPile().moveToDiscardPile(c);
                handWasFull = true;
            }
        }

        if (!Wiz.discardPile().isEmpty()) {
            AbstractCard c = Wiz.discardPile().getRandomCard(true);
            if (Wiz.hand().size() < BaseMod.MAX_HAND_SIZE)
                Wiz.discardPile().moveToHand(c);
            else
                handWasFull = true;
        }

        if (!Wiz.p().exhaustPile.isEmpty()) {
            AbstractCard c = Wiz.p().exhaustPile.getRandomCard(true);
            c.unfadeOut(); ///
            if (AbstractDungeon.player.hasPower("Corruption") && c.type == AbstractCard.CardType.SKILL) {
                c.setCostForTurn(-9);
            }
            Wiz.p().exhaustPile.removeCard(c);
            c.unhover(); //?
            c.fadingOut = false; //?

            if (Wiz.hand().size() < BaseMod.MAX_HAND_SIZE)
                Wiz.p().hand.addToHand(c);
            else {
                Wiz.p().discardPile.addToTop(c);
                handWasFull = true;
            }
        }

        if (handWasFull)
            AbstractDungeon.player.createHandIsFullDialog();

        this.isDone = true;
    }
}