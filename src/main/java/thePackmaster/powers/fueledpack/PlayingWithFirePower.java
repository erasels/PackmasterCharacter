package thePackmaster.powers.fueledpack;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.AbstractPackmasterPower;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.powers.shamanpack.IgnitePower;

import static thePackmaster.SpireAnniversary5Mod.*;
import static thePackmaster.util.Wiz.*;

public class PlayingWithFirePower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(PlayingWithFirePower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static final int SIDES= 8;

    public PlayingWithFirePower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void atStartOfTurn() {
        if (amount == 1)
            CardCrawlGame.sound.play(DIE_KEY, 0.1f);
        else if (amount >= 2 && amount <= 4)
            CardCrawlGame.sound.play(DICE_KEY, 0.1f);
        else if (amount >= 5)
            CardCrawlGame.sound.play(DICELOTS_KEY, 0.1f);

        for (int i = 0; i < amount; i++) {
            DicePower power = new DicePower(adp(), SIDES, false);
            applyToSelf(power);
            if (power.amount == SIDES || power.amount == SIDES - 1)
                applyToSelf(new IgnitePower(adp(), 1));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}