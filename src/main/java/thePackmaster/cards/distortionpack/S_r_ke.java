package thePackmaster.cards.distortionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.distortionpack.ImproveAction;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class S_r_ke extends AbstractDistortionCard {
    public final static String ID = makeID("S_r_ke");

    public S_r_ke() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
        baseMagicNumber = magicNumber = 4;

        tags.add(CardTags.STRIKE);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        ApplyPowerAction distortion = new ApplyPowerAction(m, p, new DistortionPower(m, p, this.magicNumber), this.magicNumber);
        atb(distortion);
        atb(new ImproveAction(m, this.magicNumber * 2, distortion));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }
}