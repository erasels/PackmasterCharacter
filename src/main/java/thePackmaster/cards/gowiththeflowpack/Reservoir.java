package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.gowiththeflowpack.FlowAction;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Reservoir extends AbstractHydrologistCard {
    public final static String ID = makeID("Reservoir");

    public Reservoir() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE, Subtype.WATER);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FlowAction(cards -> {
            if (cards.size() > 0) {
                addToBot(new DrawCardAction(cards.size()));
            }
        }));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}