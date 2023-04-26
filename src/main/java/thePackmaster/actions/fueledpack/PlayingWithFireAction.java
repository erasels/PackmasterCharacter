package thePackmaster.actions.fueledpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.actions.boardgamepack.RollAction;
import thePackmaster.powers.shamanpack.IgnitePower;

import java.util.ArrayList;
import java.util.function.Consumer;

import static thePackmaster.SpireAnniversary5Mod.*;
import static thePackmaster.cards.fueledpack.PlayingWithFire.IGNITE_THRESHOLD;
import static thePackmaster.util.Wiz.*;

public class PlayingWithFireAction extends AbstractGameAction {
    private final ArrayList<Integer> sides = new ArrayList<>();

    public PlayingWithFireAction(int sides, int num) {
        for (int i = 0; i < num; i++)
            this.sides.add(sides);
    }

    public void update() {
        playSFX();
        Consumer<Integer> dieConsumer = dieFace -> {
            if (dieFace > IGNITE_THRESHOLD)
                applyToSelfTop(new IgnitePower(adp(), 1));
        };
        att(new RollAction(sides, 0, dieConsumer, false));
        isDone = true;
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
