package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlickDice extends AbstractBoardCard {
    public final static String ID = makeID(FlickDice.class.getSimpleName());

    public FlickDice() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.doDmg(m, damage);
        int dice = 6;
        addToBot(new ApplyPowerAction(p, p, new DicePower(p, dice), dice));
    }

    public void upp() {
        upgradeDamage(3);
    }
}