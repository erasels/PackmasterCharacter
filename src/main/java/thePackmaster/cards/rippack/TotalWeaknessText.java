package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class TotalWeaknessText extends AbstractRippedTextCard {
    public final static String ID = makeID("TotalWeaknessText");

    public TotalWeaknessText() {
        super(ID, new TotalWeakness());
    }

    public TotalWeaknessText(TotalWeakness sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void upp() {
        upgradeBlock(2);
        upgradeMagicNumber(1);
    }
}
