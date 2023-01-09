package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.FiendFire;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.entropypack.UnbindAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Unbind extends AbstractPackmasterCard {
    public final static String ID = makeID("Unbind");
    // intellij stuff attack, enemy, uncommon, 4, 2, , , , 

    public Unbind() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new UnbindAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
    }

    public void upp() {
        upgradeDamage(2);
    }
}