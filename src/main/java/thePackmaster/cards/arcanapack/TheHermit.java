package thePackmaster.cards.arcanapack;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.patches.arcanapack.AnimatedCardsPatch;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class TheHermit extends AbstractAstrologerCard {
    public final static String ID = makeID("TheHermit");
    // intellij stuff skill, none, uncommon, , , , , , 

    public TheHermit() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        exhaust = true;

        AnimatedCardsPatch.loadFrames(this, 5, 0.1f);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SelectCardsInHandAction(1, DiscardAction.TEXT[0], (c)->true,
                (cards)->{
                    for (AbstractCard c : cards) {
                        atb(new DiscardSpecificCardAction(c));
                    }
                    for (AbstractCard c : cards) {
                        int amt = c.costForTurn == -1 ? EnergyPanel.getCurrentEnergy() : c.costForTurn;
                        if (amt >= 1)
                            atb(new DrawCardAction(amt));
                    }
                }));
    }

    public void upp() {
        exhaust = false;
        uDesc();
    }
}