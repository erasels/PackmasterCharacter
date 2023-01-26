package thePackmaster.cards.bardinspirepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Sift extends AbstractBardCard
{
    public final static String ID = makeID("Sift");
    private static final int COST = 1;
    private static final int DRAW = 1;

    public Sift()
    {
        super(ID, COST, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = DRAW;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m)
    {
        Wiz.atb(new DrawCardAction(magicNumber, new AbstractGameAction() {
            @Override
            public void update() {
                for (AbstractCard c : DrawCardAction.drawnCards) {
                    int cost = Wiz.getLogicalCardCost(c);
                    if (cost > 0) {
                        addToTop(new GainEnergyAction(c.costForTurn));
                    }
                }
                isDone = true;
            }
        }));

        if (upgraded) {
            atb(new DrawCardAction(p, magicNumber));
        }
    }

    @Override
    public void upp()
    {
    }
}
