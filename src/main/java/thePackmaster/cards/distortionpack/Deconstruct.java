package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.distortionpack.ImproveAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Deconstruct extends AbstractPackmasterCard {
    public final static String ID = makeID("Deconstruct");
    // intellij stuff skill, all_enemy, common, , , , , 4, 2

    public Deconstruct() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 4;

        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(
                (mo)->{
                    applyToEnemy(mo, new DistortionPower(mo, p, this.magicNumber));
                    atb(new ImproveAction(mo));
                }
        );
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}