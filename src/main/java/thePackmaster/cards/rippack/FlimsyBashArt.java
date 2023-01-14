package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class FlimsyBashArt extends AbstractRippedArtCard {
    public final static String ID = makeID("FlimsyBashArt");

    public FlimsyBashArt() {
        super(ID, new FlimsyBash());
    }

    public FlimsyBashArt(FlimsyBash sourceCard) {
        super(ID, sourceCard);
    }
}
