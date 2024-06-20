package thePackmaster.cards.transmutationpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class DimensionalIcicles extends AbstractHydrologistCard {
    public final static String ID = makeID("DimensionalIcicles");
    // intellij stuff attack, enemy, uncommon, 3, , 2, 1, , 

    public DimensionalIcicles() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, Subtype.ICE);
        baseDamage = 3;
        baseBlock = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < block; ++i) {
            hydrologistDamage(p, m, damage);
        }
    }

    public void upp() {
        upgradeBlock(1);
    }
}