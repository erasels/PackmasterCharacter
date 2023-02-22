package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import basemod.patches.com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen.NoCompendium;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.WardGemMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

@NoCompendium
public class WardGem extends AbstractGemsCard {
    public final static String ID = makeID("WardGem");

    //This is a deprecated card replaced by FrostGem.  The card class remains for runs
    //in-progress so they don't get drastically altered (potentially broken, not sure how
    //cardmods work when IDs dont exist).
    //Should delete this and its mod and strings in a month or so.

    public WardGem() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        cardsToPreview = new Ward();
    }

    @Override
    public AbstractCardModifier myMod() {
        return new WardGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MakeTempCardInHandAction(new Ward()));
    }


}