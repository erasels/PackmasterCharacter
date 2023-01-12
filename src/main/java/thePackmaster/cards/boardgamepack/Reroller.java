package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Reroller extends AbstractBoardCard {
    public final static String ID = makeID(Reroller.class.getSimpleName());

    public Reroller() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
        block = baseBlock = 8;
        reroll = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.doDmg(m, damage);
    }

    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }
}