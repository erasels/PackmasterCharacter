package thePackmaster.cards.alignmentpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.EasyXCostAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.arcanapack.AbstractAstrologerCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Alignment extends AbstractAstrologerCard {
    public final static String ID = makeID("Alignment");
    // intellij stuff skill, none, rare, , , , , , 

    public Alignment() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (amt, params)->{
            AbstractGameAction drawCallback = new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractCard c : DrawCardAction.drawnCards) {
                        if (c.costForTurn >= 0) {
                            c.setCostForTurn(Math.max(0, c.costForTurn - amt));
                        }
                    }
                    isDone = true;
                }
            };
            att(new DrawCardAction(amt, drawCallback));
            return true;
        }));
    }

    public void upp() {
        exhaust = false;
    }
}