package thePackmaster.cards.rimworldpack;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.actions.rimworldpack.HoldCardInMonsterAction;
import thePackmaster.actions.rimworldpack.PutCardInMonsterAction;
import thePackmaster.powers.rimworldpack.HoldCardPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TaintedWeapon extends AbstractRimCard {
    public final static String ID = makeID(TaintedWeapon.class.getSimpleName());

    public TaintedWeapon() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        damage = baseDamage = 8;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        if(!m.isDeadOrEscaped() && !m.isDying && m.currentHealth > 0)
            addToBot(new PutCardInMonsterAction(this, m));
//            addToBot(new HoldCardInMonsterAction(m, this));
    }

    public void upp() {
        upgradeDamage(3);
    }
}