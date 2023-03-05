package thePackmaster.cards.warriorpack;

import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.cardmodifiers.warriorpack.FeralDamage;
import thePackmaster.cards.AbstractPackmasterCard;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.applyToEnemy;
import static thePackmaster.util.Wiz.atb;

public class StormStrike extends AbstractWarriorCard {

    public final static String ID = makeID(StormStrike.class.getSimpleName());

    private static final int COST = 1;

    public StormStrike(){
        super(ID, COST, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        tags.add(CardTags.STRIKE);
        baseDamage = 7;
        DamageModifierManager.addModifier(this, new FeralDamage());
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToTop(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.1F));
            this.addToTop(new SFXAction("ORB_LIGHTNING_EVOKE"));
        }
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
        upMagic(1);
    }
}
