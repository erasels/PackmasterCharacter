package thePackmaster.powers.fueledpack;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.fueledpack.PlayingWithFireAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.util.Wiz.*;

public class PlayingWithFirePower extends AbstractPackmasterPower {
    public static String POWER_ID = SpireAnniversary5Mod.makeID(PlayingWithFirePower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    public static final int SIDES = 6;

    public PlayingWithFirePower(int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, false, adp(), amount);
    }

    @Override
    public void atStartOfTurn() {
        atb(new PlayingWithFireAction(SIDES, amount));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}