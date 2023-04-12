package thePackmaster.actions.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.boardgamepack.AdvantagePower;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.powers.boardgamepack.OneTimeAdvantagePower;

import static thePackmaster.util.Wiz.*;

public class RollAction extends AbstractGameAction {
    private final int sides;
    private final boolean sound;

    public RollAction(int sides, int num) {
        this.sides = sides;
        amount = num;
        sound = true;
    }
    public RollAction(int sides, int num, boolean sound) {
        this.sides = sides;
        this.sound = sound;
        amount = num;
    }
    public void update() {
        int diceSum = 0;
        for (int i = 0; i < amount; i++)
            diceSum += roll(sides);
        applyToSelf(new DicePower(adp(), diceSum, sound));
        isDone = true;
    }

    private static int roll(int sides) {
        int curRoll = 0;
        int advantage = getAdvantage();
        for(int roll = 0; roll <= advantage; roll++)
        {
            int newRoll = AbstractDungeon.cardRandomRng.random(sides - 1) + 1;
            //ADD VFX HERE
            if(newRoll > curRoll)
                curRoll = newRoll;
        }
        return curRoll;
    }

    private static int getAdvantage() {
        //ADD ADVANTAGE CHECKS HERE
        int adv = 0;
        if(adp().hasPower(OneTimeAdvantagePower.POWER_ID))
        {
            adv += adp().getPower(OneTimeAdvantagePower.POWER_ID).amount;
            att(new RemoveSpecificPowerAction(adp(), adp(), adp().getPower(OneTimeAdvantagePower.POWER_ID)));
        }
        if(adp().hasPower(AdvantagePower.POWER_ID))
            adv += adp().getPower(AdvantagePower.POWER_ID).amount;
        return adv;
    }
}
