package thePackmaster.powers.boardgamepack;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import thePackmaster.actions.boardgamepack.RollAction;
import thePackmaster.powers.AbstractPackmasterPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class PerceptionCheckPower extends AbstractPackmasterPower {
    public static final String POWER_ID = makeID(PerceptionCheckPower.class.getSimpleName());
    public static final String NAME = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).NAME;
    public static final String[] DESCRIPTIONS = CardCrawlGame.languagePack.getPowerStrings(POWER_ID).DESCRIPTIONS;
    private static final int SIDES = 8;

    public PerceptionCheckPower(AbstractCreature owner, int amount) {
        super(POWER_ID, NAME, PowerType.BUFF, true, owner, amount);
    }

    @Override
    public void atStartOfTurn()
    {
        if(!owner.hasPower(DicePower.POWER_ID))
            atb(new RollAction(SIDES, 1));
        // When I rewrote this part, the original code ignored amount.  Is the below intended?
        // Is it intended not to stack for balance reasons?
        // This is going to surprise players when it doesn't stack
        // Also non-stacking uncommon powers are a bad idea
        // If there is a concern about this being too powerful AND it doesn't stack it should definitely be rare

        // atb(new RollAction(SIDES, AMOUNT);
    }

    @Override
    public void updateDescription() {
        if(amount == 1)
            description = DESCRIPTIONS[0];
        else
            description = DESCRIPTIONS[1]+ amount + DESCRIPTIONS[2];
    }

}
