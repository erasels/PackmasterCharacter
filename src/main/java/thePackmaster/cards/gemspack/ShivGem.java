package thePackmaster.cards.gemspack;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cardmodifiers.gemspack.ShivGemMod;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ShivGem extends AbstractGemsCard {
    public final static String ID = makeID("ShivGem");

    public ShivGem() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
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