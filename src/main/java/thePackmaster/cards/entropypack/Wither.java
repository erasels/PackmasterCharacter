package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.entropypack.RuinPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Wither extends AbstractEntropyCard {
    public final static String ID = makeID("Wither");

    public Wither() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        if (!upgraded) {
            applyToEnemy(m, new RuinPower(m, this.magicNumber));
        }
        else {
            forAllMonstersLiving(
                    (mo)->applyToEnemy(mo, new RuinPower(mo, this.magicNumber))
            );
        }
    }

    public void upp() {
    }
}