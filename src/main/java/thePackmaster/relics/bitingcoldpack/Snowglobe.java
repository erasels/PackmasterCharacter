package thePackmaster.relics.bitingcoldpack;

import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.BitingColdPack;
import thePackmaster.relics.AbstractPackmasterRelic;

public class Snowglobe extends AbstractPackmasterRelic {
    public static final String ID = SpireAnniversary5Mod.makeID("Snowglobe");

    public Snowglobe() {
        super(ID, RelicTier.COMMON, LandingSound.CLINK, BitingColdPack.ID);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + 1 + this.DESCRIPTIONS[1];
    }

    // Hey there! Welcome to my code!
    // I know that this relic doesn't look to be doing anything...
    // Its functionality is actually in the FrostbiteDamageAction
    // If you want to see this relic's function, go there ^^
}
