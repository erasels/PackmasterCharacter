package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.dimensiongatepack.MagicMissilePower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.MAGIC;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ForceBolt extends AbstractWardenCard {
    public final static String ID = makeID("ForceBolt");

    private static final int DAMAGE = 4;
    private static final int DAMAGE_UPGRADE = 2;
    private static final int MAGIC_BASE = 3;
    private static final int MAGIC_UP = 1;

    public ForceBolt() {
        super(ID, 0, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC_BASE;
        tags.add(MAGIC);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToSelf(new MagicMissilePower(p, magicNumber));
    }

    public void upp() {
        upgradeDamage(DAMAGE_UPGRADE);
        upgradeMagicNumber(MAGIC_UP);
    }
}
