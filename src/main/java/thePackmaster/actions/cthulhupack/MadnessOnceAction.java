package thePackmaster.actions.cthulhupack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class MadnessOnceAction extends AbstractGameAction {
    private AbstractPlayer p;

    public MadnessOnceAction() {
        this.actionType = ActionType.CARD_MANIPULATION;
        this.p = AbstractDungeon.player;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            boolean betterPossible = false;
            boolean possible = false;

            for (AbstractCard c : this.p.hand.group) {
                if (c.costForTurn > 0) {
                    betterPossible = true;
                } else if (c.cost > 0) {
                    possible = true;
                }
            }

            if (betterPossible || possible) {
                this.findAndModifyCard(betterPossible);
            }
        }

        this.tickDuration();
    }

    private void findAndModifyCard(boolean better) {
        AbstractCard c = this.p.hand.getRandomCard(AbstractDungeon.cardRandomRng);
        if (better) {
            if (c.costForTurn > 0) {
                c.setCostForTurn(0);
                c.superFlash(Color.GOLD.cpy());
            } else {
                this.findAndModifyCard(better);
            }
        } else if (c.cost > 0) {
            c.setCostForTurn(0);
            c.superFlash(Color.GOLD.cpy());
        } else {
            this.findAndModifyCard(better);
        }

    }
}
