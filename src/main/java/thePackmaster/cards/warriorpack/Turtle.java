package thePackmaster.cards.warriorpack;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.warriorpack.TurtlePower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Turtle extends AbstractPackmasterCard {

    public final static String ID = makeID(Turtle.class.getSimpleName());

    private static final int COST = 2;

    public Turtle(){
        super(ID, COST, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 7;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainBlockAction(p, block));
        applyToSelf(new TurtlePower(p, 1));
    }

    @Override
    public void upp() {
        upgradeBlock(4);
    }
}
