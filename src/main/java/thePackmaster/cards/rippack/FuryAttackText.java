package thePackmaster.cards.rippack;

import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
@AutoAdd.Ignore
public class FuryAttackText extends AbstractRippedTextCard {
    public final static String ID = makeID("FuryAttackText");

    public FuryAttackText() {
        super(ID, new FuryAttack());
    }

    public FuryAttackText(FuryAttack sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void upp() {
        upgradeDamage(5);
    }
}
