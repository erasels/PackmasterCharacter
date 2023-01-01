package thePackmaster.relics;

import thePackmaster.ThePackmaster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PMBoosterPack extends AbstractPackmasterRelic {
    public static final String ID = makeID("PMBoosterPack");

    public PMBoosterPack() {
        super(ID, RelicTier.COMMON, LandingSound.FLAT);
    }
}
