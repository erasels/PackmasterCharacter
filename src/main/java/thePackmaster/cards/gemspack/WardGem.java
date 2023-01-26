package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.WardGemMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class WardGem extends AbstractGemsCard {
    public final static String ID = makeID("WardGem");

    public WardGem() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
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