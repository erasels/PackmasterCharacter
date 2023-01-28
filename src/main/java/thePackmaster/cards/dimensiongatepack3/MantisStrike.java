package thePackmaster.cards.dimensiongatepack3;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
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
        baseDamage = 7;
        tags.add(CardTags.STRIKE);

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelfDamageAction(new DamageInfo(p, 3, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.FIRE));
        Wiz.doDmg(m, damage, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        Wiz.doAllDmg(damage, AbstractGameAction.AttackEffect.NONE, DamageInfo.DamageType.NORMAL, false);

    }

    public void upp() {
        upgradeDamage(2);
    }
}