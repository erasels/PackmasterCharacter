package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.actions.distortionpack.ImproveAction;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class Darken extends AbstractDistortionCard {
    public final static String ID = makeID("Darken");
    // intellij stuff skill, enemy, uncommon, , , , , 7, 

    public Darken() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 7;
        baseSecondMagic = secondMagic = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            int amt = magicNumber;

            for (AbstractPower pow : m.powers) {
                if (pow.type == AbstractPower.PowerType.DEBUFF && !pow.ID.equals(DistortionPower.POWER_ID)) {
                    amt += secondMagic;
                    break;
                }
            }

            ApplyPowerAction distortion = new ApplyPowerAction(m, p, new DistortionPower(m, p, amt), amt);
            atb(distortion);
            atb(new ImproveAction(m, amt, distortion));
        }
    }

    public void upp() {
        upgradeMagicNumber(2);
        upgradeSecondMagic(2);
    }
}