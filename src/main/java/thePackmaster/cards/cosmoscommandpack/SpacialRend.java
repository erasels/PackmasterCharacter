package thePackmaster.cards.cosmoscommandpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.distortionpack.DistortionPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;

public class SpacialRend extends AbstractPackmasterCard {
    public final static String ID = makeID("SpacialRend");

    public SpacialRend() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
        magicNumber = baseMagicNumber = 4;
        secondMagic = baseSecondMagic = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        applyToEnemy(m, new DistortionPower(m, p, magicNumber));
        applyToEnemy(m, new VulnerablePower(m, secondMagic, false));
    }

    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}