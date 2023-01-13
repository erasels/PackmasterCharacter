package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class TotalWeaknessArt extends AbstractRippedArtCard {
    public final static String ID = makeID("TotalWeaknessArt");

    public TotalWeaknessArt() {
        super(ID, new TotalWeakness());
    }

    public TotalWeaknessArt(TotalWeakness sourceCard) {
        super(ID, sourceCard);
    }
}
