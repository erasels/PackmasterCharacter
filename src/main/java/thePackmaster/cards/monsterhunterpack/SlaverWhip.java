package thePackmaster.cards.monsterhunterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import thePackmaster.actions.monsterhunterpack.WhipFollowUpAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class SlaverWhip extends AbstractMonsterHunterCard {
    public final static String ID = makeID("SlaverWhip");

    public static final int DAMAGE = 12;
    public static final int UPG_DAMAGE = 3;
    public static final int MAGIC = 1;

    public SlaverWhip() {
        super(ID, 1, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = damage = DAMAGE;
        baseMagicNumber = magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
       addToBot(new DamageAction(m, new DamageInfo(m, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
       addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
       addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m, magicNumber, false), magicNumber));
       addToBot(new WhipFollowUpAction(m, this));
    }

    public void upp() {
        upgradeDamage(UPG_DAMAGE);
    }
}