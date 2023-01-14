package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class InspirationArt extends AbstractRippedArtCard {
    public final static String ID = makeID("InspirationArt");

    public InspirationArt() {
        super(ID, new Inspiration());
    }

    public InspirationArt(Inspiration sourceCard) {
        super(ID, sourceCard);
    }
}
