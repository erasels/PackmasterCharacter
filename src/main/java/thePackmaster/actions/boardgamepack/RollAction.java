package thePackmaster.actions.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.boardgamepack.AdvantagePower;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.powers.boardgamepack.OneTimeAdvantagePower;

import java.util.ArrayList;
import java.util.function.Consumer;

import static thePackmaster.util.Wiz.*;

public class RollAction extends AbstractGameAction {
    private final ArrayList<Integer> sides = new ArrayList<>();
    private final boolean sound;
    private final Consumer<Integer> dieConsumer;
    private int modifier = 0;

    public RollAction(int sides, int num) {
        this(sides, num, true);
    }

    public RollAction(int sides, int num, boolean sound) {
        this.sound = sound;
        dieConsumer = null;
        for (int i = 0; i < num; i++)
            this.sides.add(sides);
    }

    public RollAction(ArrayList<Integer> sides, int modifier, boolean sound) {
        this(sides, modifier, null, sound);
    }

    public RollAction(ArrayList<Integer> sides, int modifier, Consumer<Integer> dieConsumer, boolean sound) {
        this.sound = sound;
        this.modifier = modifier;
        this.sides.addAll(sides);
        this.dieConsumer = dieConsumer;
    }

    public void update() {
        int diceSum = 0;
        int advantage = getAdvantage();
        for (Integer side : sides)
            diceSum += roll(side, advantage);
        diceSum += modifier;
        applyToSelfTop(new DicePower(adp(), diceSum, sound));
        isDone = true;
    }

    private int roll(int sides, int advantage) {
        int curRoll = 0;
        for(int i = 0; i <= advantage; i++)
        {
            int newRoll = AbstractDungeon.cardRandomRng.random(sides - 1) + 1;
            if(newRoll > curRoll)
                curRoll = newRoll;
        }
        if (dieConsumer != null)
            dieConsumer.accept(curRoll);
        return curRoll;
    }

    private static int getAdvantage() {
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
