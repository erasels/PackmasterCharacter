package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.DelayedDiceRollAction;
import thePackmaster.powers.boardgamepack.AdvantagePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LevelUp extends AbstractBoardCard {
    public final static String ID = makeID(LevelUp.class.getSimpleName());

    public LevelUp() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new ApplyPowerAction(p, p, new AdvantagePower(p, magicNumber), magicNumber));
        addToBot(new DelayedDiceRollAction(6, 2));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}