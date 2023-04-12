package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.DelayedDiceRollAction;
import thePackmaster.actions.boardgamepack.RollAction;
import thePackmaster.powers.boardgamepack.AdvantagePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class LevelUp extends AbstractBoardCard {
    public final static String ID = makeID(LevelUp.class.getSimpleName());
    private final static int SIDES = 6;
    private final static int NUM_DICE = 2;

    public LevelUp() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AdvantagePower(p, magicNumber), magicNumber));
        atb( new DelayedDiceRollAction(SIDES, NUM_DICE));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}