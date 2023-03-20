package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.distortionpack.ImproveAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class Deconstruct extends AbstractDistortionCard {
    public final static String ID = makeID("Deconstruct");
    // intellij stuff skill, all_enemy, common, , , , , 4, 2

    public Deconstruct() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 5;

        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(
                (mo)->{
                    ApplyPowerAction distortion = new ApplyPowerAction(mo, p, new DistortionPower(mo, p, this.magicNumber), this.magicNumber);
                    atb(distortion);
                    atb(new ImproveAction(mo, this.magicNumber, distortion));
                }
        );
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}