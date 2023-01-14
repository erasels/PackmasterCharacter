package thePackmaster.cards.rippack;

import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
public class HazardousStrikeText extends AbstractRippedTextCard {
    public final static String ID = makeID("HazardousStrikeText");

    public HazardousStrikeText() {
        super(ID, new HazardousStrike());
        tags.add(CardTags.STRIKE);
    }

    public HazardousStrikeText(HazardousStrike sourceCard) {
        super(ID, sourceCard);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void upp() {
        upgradeDamage(6);
    }
}
