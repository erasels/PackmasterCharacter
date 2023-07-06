package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.RollAction;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class FlickDice extends AbstractBoardCard {
    public final static String ID = makeID(FlickDice.class.getSimpleName());
    private static final int SIDES = 6;

    public FlickDice() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage);
        atb(new RollAction(SIDES, 1));
    }

    public void upp() {
        upgradeDamage(3);
    }
}