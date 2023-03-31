package thePackmaster.cards.overwhelmingpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.louseList;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Approach extends AbstractOverwhelmingCard {
    public final static String ID = makeID("Approach");

    public Approach() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);

        this.magicNumber = this.baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(this.magicNumber));
        addToBot(new DrawCardAction(1, approachCallback()));
    }

    private static AbstractGameAction approachCallback() {
        return new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;

                if (DrawCardAction.drawnCards.isEmpty()) //couldn't draw
                    return;

                for (AbstractCard c : DrawCardAction.drawnCards) {
                    if (c.costForTurn == 1) {
                        return;
                    }
                }

                addToTop(new DrawCardAction(1, approachCallback()));
            }
        };
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}