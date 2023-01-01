package thePackmaster.relics;

import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PMCollection extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMCollection");

    public PMCollection() {
        super(ID, RelicTier.SHOP, LandingSound.FLAT);
    }
}
