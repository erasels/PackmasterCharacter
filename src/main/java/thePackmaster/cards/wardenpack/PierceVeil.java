package thePackmaster.cards.wardenpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.wardenpack.PierceVeilAction;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class PierceVeil extends AbstractPackmasterCard {
    public final static String ID = makeID("PierceVeil");

    private static final int DAMAGE = 5;
    private static final int DAMAGE_UPGRADE = 3;

    public PierceVeil() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        this.addToBot(new PierceVeilAction(magicNumber));
    }

    public void upp() {
            upgradeDamage(DAMAGE_UPGRADE);
            upgradeMagicNumber(1);
    }
}
