package thePackmaster.cards.rippack;

import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
@AutoAdd.Ignore
public class FragileShrugArt extends AbstractRippedArtCard {
    public final static String ID = makeID("FragileShrugArt");

    public FragileShrugArt() {
        super(ID, new FragileShrug());
    }

    public FragileShrugArt(FragileShrug sourceCard) {
        super(ID, sourceCard);
    }
}
