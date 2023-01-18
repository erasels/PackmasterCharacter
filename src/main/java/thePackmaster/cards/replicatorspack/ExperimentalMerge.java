package thePackmaster.cards.replicatorspack;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ShuffleAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ExperimentalMerge extends AbstractReplicatorCard {


    public final static String ID = makeID("ExperimentalMerge");

    public ExperimentalMerge() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        baseBlock = block = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, block));
        if (p.discardPile.size() > 0) {
            atb(new EmptyDeckShuffleAction());
            atb(new ShuffleAction(p.drawPile, false));
        }
        atb(new DrawCardAction(p, this.magicNumber));
    }

    public void upp() {
        upgradeBlock(3);
    }
}