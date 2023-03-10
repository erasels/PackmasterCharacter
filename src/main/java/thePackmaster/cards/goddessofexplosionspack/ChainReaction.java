package thePackmaster.cards.goddessofexplosionspack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.goddessofexplosionspack.ChainReactionPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ChainReaction extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("ChainReaction");

    private static final int DAMAGE = 6;
    private static final int MAGIC = 6;
    private static final int DAMAGE_UP = 2;
    private static final int MAGIC_UP = 2;

    public ChainReaction() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        Wiz.atb(new ApplyPowerAction(m,p, new ChainReactionPower(m,magicNumber),magicNumber,true));
    }

    @Override
    public void upp() {
            upgradeDamage(DAMAGE_UP);
            upgradeMagicNumber(MAGIC_UP);
    }
}