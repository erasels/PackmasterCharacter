package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import com.megacrit.cardcrawl.orbs.Lightning;
import thePackmaster.cardmodifiers.gemspack.ShivGemMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShivGem extends AbstractGemsCard {
    public final static String ID = makeID("ShivGem");

    //This is a deprecated card replaced by LightningGem.  The card class remains for runs
    //in-progress so they don't get drastically altered (potentially broken, not sure how
    //cardmods work when IDs dont exist).
    //Should delete this and its mod and strings in a month or so.

    public ShivGem() {
        super(ID, 0, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF);
        cardsToPreview = new Shiv();
    }

    @Override
    public AbstractCardModifier myMod() {
        return new ShivGemMod();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new MakeTempCardInHandAction(new Shiv()));
    }

 
}