package thePackmaster.cards.rippack;

import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
@AutoAdd.Ignore
public class ViciousCycleArt extends AbstractRippedArtCard {
    public final static String ID = makeID("ViciousCycleArt");

    public ViciousCycleArt() {
        super(ID, new ViciousCycle());
        exhaust = true;
    }

    public ViciousCycleArt(ViciousCycle sourceCard) {
        super(ID, sourceCard);
    }
}
