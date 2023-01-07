package thePackmaster.cards.bardinspirepack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.FetchAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.defect.DoubleEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

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
        if (p.drawPile.isEmpty()) {
            atb(new EmptyDeckShuffleAction());
        }
        atb(
                new FetchAction(
                        AbstractDungeon.player.drawPile,
                        c -> AbstractDungeon.player.drawPile.group.indexOf(c) > AbstractDungeon.player.drawPile.size() - magicNumber - 1,
                        cards -> {
                            for (AbstractCard c : cards) {
                                if (c.costForTurn > 0) {
                                    addToTop(new GainEnergyAction(c.costForTurn));
                                } else if (c.costForTurn == -1) {
                                    // X-cost
                                    addToTop(new DoubleEnergyAction());
                                }
                            }
                            AbstractDungeon.player.hand.refreshHandLayout();
                        }
                )
        );
        if (upgraded) {
            atb(new DrawCardAction(p, magicNumber));
        }
    }

    @Override
    public void upp()
    {
    }
}
