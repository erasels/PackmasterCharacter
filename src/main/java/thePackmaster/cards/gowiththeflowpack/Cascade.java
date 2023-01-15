package thePackmaster.cards.gowiththeflowpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.gowiththeflowpack.FlowAction;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Cascade extends AbstractHydrologistCard {
    public final static String ID = makeID("Cascade");

    public Cascade() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, Subtype.WATER);
        baseBlock = block = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FlowAction(cards -> {
            for (AbstractCard card : cards) {
                addToTop(new GainBlockAction(p, p, block));
            }
        }));
    }

    public void upp() {
        upgradeBlock(1);
    }
}