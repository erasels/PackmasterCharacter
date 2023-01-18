package thePackmaster.cards.rippack;

import basemod.AutoAdd;
import basemod.patches.com.megacrit.cardcrawl.dungeons.AbstractDungeon.NoPools;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
@NoPools
@AutoAdd.Ignore
public class FurySkillText extends AbstractRippedTextCard {
    public final static String ID = makeID("FurySkillText");

    public FurySkillText() {
        super(ID, new FurySkill());
    }

    public FurySkillText(FurySkill sourceCard) {
        super(ID, sourceCard);
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }
}
