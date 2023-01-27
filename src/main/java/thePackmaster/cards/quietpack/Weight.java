package thePackmaster.cards.quietpack;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;


public class Weight extends AbstractQuietCard {
    public final static String ID = makeID("Weight");

    public Weight() {
        super(ID, -2, CardType.STATUS, CardRarity.SPECIAL, CardTarget.NONE, CardColor.COLORLESS);
        selfRetain = true;
    }

    @Override
    public void triggerOnManualDiscard() {
        AbstractCard c = this;
        att(new AbstractGameAction() {
            @Override
            public void update() {
                adp().discardPile.moveToExhaustPile(Weight.this);
                isDone = true;
            }
        });
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upp() {
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }
}


