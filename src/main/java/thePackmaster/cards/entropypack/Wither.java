package thePackmaster.cards.entropypack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.entropypack.RuinPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Wither extends AbstractPackmasterCard {
    public final static String ID = makeID("Wither");
    // intellij stuff skill, enemy, common, 3, , , , 8, 

    public Wither() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 8;
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
        uDesc();
    }
}