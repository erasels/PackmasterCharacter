package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.boardgamepack.ThrowChipsAction;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class ThrowChips extends AbstractBoardCard {
    public final static String ID = makeID(ThrowChips.class.getSimpleName());

    public ThrowChips() {
        super(ID, -1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        damage = baseDamage = 8;
        reroll = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ThrowChipsAction(this, freeToPlayOnce, energyOnUse, damage));
    }

    public void upp() {
        upgradeDamage(3);
    }
}