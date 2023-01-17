package thePackmaster.cards.psychicpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.psychicpack.OccultInDrawAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class PhantomStep extends AbstractPsychicCard {
    public final static String ID = makeID("PhantomStep");
    // intellij stuff skill, none, uncommon, , , 14, 4, , 

    public PhantomStep() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new OccultInDrawAction());
    }

    public void upp() {
        upgradeBlock(4);
    }
}