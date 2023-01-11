package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.DelayedDiceLevelUpAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.boardgamepack.AdvantagePower;
import thePackmaster.powers.boardgamepack.DicePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class LevelUp extends AbstractBoardCard {
    public final static String ID = makeID(LevelUp.class.getSimpleName());

    public LevelUp() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        magicNumber = baseMagicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new ApplyPowerAction(p, p, new AdvantagePower(p, magicNumber), magicNumber));
        addToBot(new DelayedDiceLevelUpAction());
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}