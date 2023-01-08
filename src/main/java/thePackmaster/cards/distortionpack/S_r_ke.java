package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.distortionpack.ImproveAction;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class S_r_ke extends AbstractPackmasterCard {
    public final static String ID = makeID("S_r_ke");
    // intellij stuff ATTACK, ENEMY, COMMON, 7, 2, , , 2, 1

    public S_r_ke() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 7;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        ApplyPowerAction distortion = new ApplyPowerAction(m, p, new DistortionPower(m, p, this.magicNumber), this.magicNumber);
        atb(distortion);
        atb(new ImproveAction(m, distortion));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }
}