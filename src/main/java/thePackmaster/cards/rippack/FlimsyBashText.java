package thePackmaster.cards.rippack;

import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
@AutoAdd.Ignore
public class FlimsyBashText extends AbstractRippedTextCard {
    public final static String ID = makeID("FlimsyBashText");

    public FlimsyBashText() {
        super(ID, new FlimsyBash());
    }

    public FlimsyBashText(FlimsyBash sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}
