package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.RollAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class RollToMove extends AbstractBoardCard {
    public final static String ID = makeID(RollToMove.class.getSimpleName());

    static int DICE = 6;

    public RollToMove() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        block = baseBlock = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new RollAction(DICE, 2));
    }

    public void upp() {
        upgradeBlock(4);
    }
}