package thePackmaster.actions.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static thePackmaster.SpireAnniversary5Mod.modID;

public class RollDiceAction extends AbstractGameAction {

    int advantage;
    List<Integer> dice = new ArrayList<>();

    public RollDiceAction(List<Integer> dice) {
        this(dice, 0);
    }

    public RollDiceAction(List<Integer> dice, int advantage) {
        for (int diceSize: dice)
            this.dice.add(diceSize);
        this.advantage = advantage;
    }


    public void update() {
        //ADD DICE ROLL VISUALS HERE
//        int diceTotal = 0;
        for(int numDice = 0; numDice < dice.size(); numDice++)
        {
            int curRoll = 0;
            for(int roll = 0; roll <= advantage; roll++)
            {
                int newRoll = AbstractDungeon.miscRng.random(dice.get(numDice) - 1) + 1;
                if(newRoll > curRoll)
                    curRoll = newRoll;
            }
//            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
//                    AbstractDungeon.player, AbstractDungeon.player, new DicePower(AbstractDungeon.player, curRoll, dice.get(numDice)), curRoll));
//            diceTotal += curRoll;
        }

        //int rand = new Random().nextInt(4);
        //addToTop(new SFXAction(modID + ":dice1"));

//        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
//                AbstractDungeon.player, AbstractDungeon.player, new DicePower(AbstractDungeon.player, diceTotal, diceSize), diceTotal));

        this.isDone = true;
    }
}