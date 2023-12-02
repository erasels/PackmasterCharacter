package thePackmaster.actions.weaponspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import thePackmaster.orbs.weaponspack.AbstractWeaponOrb;

import java.util.ArrayList;

public class DeadlyArsenalAction extends AbstractGameAction {

    public DeadlyArsenalAction(AbstractPlayer source) {
        this.source = source;
        this.actionType = ActionType.WAIT;
    }

    public void update() {

        ArrayList<String> uniqueWeaponsList = new ArrayList<>();

        for (AbstractOrb orb : AbstractDungeon.player.orbs) {
            if (orb instanceof AbstractWeaponOrb) {
                if (!uniqueWeaponsList.contains(orb.ID)) {
                    uniqueWeaponsList.add(orb.ID);
                }
            }
        }

        int amountOfCardsToDraw = 1 + uniqueWeaponsList.size();
        AbstractDungeon.actionManager.addToTop(new DrawCardAction(this.source, amountOfCardsToDraw));

        this.isDone = true;
    }
}
