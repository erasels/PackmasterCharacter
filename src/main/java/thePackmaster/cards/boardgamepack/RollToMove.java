package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DrawCardNextTurnPower;
import thePackmaster.actions.boardgamepack.RollDiceAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.boardgamepack.DicePower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class RollToMove extends AbstractBoardCard {
    public final static String ID = makeID(RollToMove.class.getSimpleName());

    static int DICE = 6;

    public RollToMove() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new ApplyPowerAction(p, p, new DicePower(p, DICE), DICE));
        addToBot(new ApplyPowerAction(p, p, new DicePower(p, DICE), DICE));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}