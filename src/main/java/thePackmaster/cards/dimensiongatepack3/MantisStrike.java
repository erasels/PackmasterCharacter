package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.actions.dimensiongatepack.SelfDamageAction;
import thePackmaster.cards.dimensiongateabstracts.AbstractDimensionalCardInscryp;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MantisStrike extends AbstractDimensionalCardInscryp {
    public final static String ID = makeID("MantisStrike");

    public MantisStrike() {
        super(ID, 1, CardRarity.COMMON, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 3;
        baseSecondDamage = secondDamage = 7;
        tags.add(CardTags.STRIKE);
        baseMagicNumber = magicNumber = 2;

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelfDamageAction(new DamageInfo(p, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        Wiz.doDmg(m, secondDamage, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);

        for (int i = 0; i < magicNumber; i++) {
            addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }

    }

    public void upp() {
        upgradeDamage(1);
        upgradeSecondDamage(2);
    }
}