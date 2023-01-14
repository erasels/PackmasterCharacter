package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class FragileShrugText extends AbstractRippedTextCard {
    public final static String ID = makeID("FragileShrugText");

    public FragileShrugText() {
        super(ID, new FragileShrug());
    }

    public FragileShrugText(FragileShrug sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}
