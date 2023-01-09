package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class IronShield extends AbstractPackmasterCard {
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


