package thePackmaster.cards.oraclepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class BrowsingFate extends AbstractOracleCard {

    public final static String ID = makeID("BrowsingFate");
    // intellij stuff power, self, rare, , , , , 3, 1

    public BrowsingFate() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                addToTop(new DiscardAction(Wiz.adp(), Wiz.adp(), Wiz.hand().size(),true));
                isDone = true;
            }
        });
        addToBot(new ScryAction(magicNumber));
        addToBot(new DrawCardAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}
