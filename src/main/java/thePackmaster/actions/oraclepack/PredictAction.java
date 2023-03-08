package thePackmaster.actions.oraclepack;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.cardmodifiers.oraclepack.AbstractPredictMod;
import thePackmaster.cards.oraclepack.Prophecy;
import thePackmaster.util.Wiz;

import java.util.ArrayList;

public class PredictAction extends AbstractGameAction {

    private AbstractPredictMod predictMod;

    public PredictAction(AbstractPredictMod predictMod) {
        this.predictMod = predictMod;
    }

    private ArrayList<Prophecy> getProphecies() {
        ArrayList<Prophecy> prophecies = new ArrayList<>();
        for (AbstractCard c : Wiz.drawPile().group) {
            if (c instanceof Prophecy) {
                prophecies.add((Prophecy) c);
            }
        }
        for (AbstractCard c : Wiz.hand().group) {
            if (c instanceof Prophecy) {
                prophecies.add((Prophecy) c);
            }
        }
        for (AbstractCard c : Wiz.discardPile().group) {
            if (c instanceof Prophecy) {
                prophecies.add((Prophecy) c);
            }
        }
        return prophecies;
    }

    @Override
    public void update() {
        ArrayList<Prophecy> prophecies = getProphecies();

        if (prophecies.isEmpty()) {
            Prophecy proph = new Prophecy();
            CardModifierManager.addModifier(proph,predictMod);
            addToTop(new MakeTempCardInDiscardAction(proph,1));
            isDone = true;
        } else {
            for (Prophecy p : prophecies) {
                if (CardModifierManager.modifiers(p).stream().anyMatch((mod) -> mod.identifier(p).equals(predictMod.identifier(p)))) {
                    CardModifierManager.modifiers(p).stream()
                            .filter((mod) -> mod.identifier(p).equals(predictMod.identifier(p)))
                            .forEach((mod) -> ((AbstractPredictMod) mod).updateAmount(p, ((AbstractPredictMod) mod).amount + predictMod.amount));
                } else {
                    CardModifierManager.addModifier(p, predictMod);
                }
                p.initializeDescription();
            }
            isDone = true;
        }
    }
}
