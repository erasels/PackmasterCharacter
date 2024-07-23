package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class IronShield extends AbstractQuietCard {
    public final static String ID = makeID("IronShield");

    public IronShield() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseBlock = 11;
        cardsToPreview = new Weight();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        this.addToBot(new MakeTempCardInHandAction(cardsToPreview.makeCopy()));
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }
}


