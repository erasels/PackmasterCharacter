package thePackmaster.cards.cthulhupack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.distortionpack.DistortionPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FlailingTendril extends AbstractCthulhuCard {
    public final static String ID = makeID("FlailingTendril");

    private static final int ATTACK_DMG = 9;
    private static final int UPGRADE_PLUS_ATTACK_DMG = 1;
    private static final int UPGRADE_PLUS_DISTORTION_AMT = 1;
    private static final int DISTORTION_AMT = 2;

    public FlailingTendril() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = ATTACK_DMG;
        magicNumber = baseMagicNumber = DISTORTION_AMT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        Wiz.applyToEnemy(m, new DistortionPower(m, p, magicNumber));
    }

    public void upp() {
        upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
        upgradeMagicNumber(UPGRADE_PLUS_DISTORTION_AMT);
    }
}