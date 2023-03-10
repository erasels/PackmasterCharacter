package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.boardgamepack.DicePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RollToMove extends AbstractBoardCard {
    public final static String ID = makeID(RollToMove.class.getSimpleName());

    static int DICE = 6;

    public RollToMove() {
        super(ID, 2, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        block = baseBlock = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new ApplyPowerAction(p, p, new DicePower(p, DICE), DICE));
        addToBot(new ApplyPowerAction(p, p, new DicePower(p, DICE), DICE));
    }

    public void upp() {
        upgradeBlock(4);
    }
}