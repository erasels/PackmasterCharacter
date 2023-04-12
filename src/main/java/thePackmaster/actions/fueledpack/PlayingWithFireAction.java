package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.powers.boardgamepack.AdvantagePower;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.powers.boardgamepack.OneTimeAdvantagePower;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.*;
import static thePackmaster.util.Wiz.*;

public class PlayingWithFireAction extends AbstractGameAction {
    private int sides;

    public PlayingWithFireAction(int sides, int num) {
        this.sides = sides;
        amount = num;
    }

    public void update() {
        playSFX();
        int diceSum = 0;
        for (int i = 0; i < amount; i++) {
            int dieFace = roll(sides);
            diceSum += dieFace;

            if (dieFace == sides || dieFace == sides - 1)
                applyToSelf(new IgnitePower(adp(), 1));
        }

        applyToSelf(new DicePower(adp(), diceSum, false));
        isDone = true;
    }

    private static int roll(int sides) {
        int curRoll = 0;
        int advantage = getAdvantage();
        for(int roll = 0; roll <= advantage; roll++)
        {
            int newRoll = AbstractDungeon.cardRandomRng.random(sides - 1) + 1;
            if(newRoll > curRoll)
                curRoll = newRoll;
        }
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

    private void playSFX() {
        if (amount == 1)
            CardCrawlGame.sound.play(DIE_KEY, 0.1f);
        else if (amount >= 2 && amount <= 4)
            CardCrawlGame.sound.play(DICE_KEY, 0.1f);
        else if (amount >= 5)
            CardCrawlGame.sound.play(DICELOTS_KEY, 0.1f);
    }
}
