package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.DieDieDieEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Tragedy extends AbstractBatterCard {
    public final static String ID = makeID("Tragedy");

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 3;

    public Tragedy() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseDamage = DAMAGE;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isCrit()) {
            this.addToBot(new VFXAction(new DieDieDieEffect(), 0.7F));
        }
        else {
            this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        }
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        super.calculateCardDamage(mo);

        if (isCrit()) {
            this.damage *= 1.5;

            for (int a=0; a<this.multiDamage.length; a++) {
                this.multiDamage[a] *= 1.5;
            }
        }

        isDamageModified = damage != baseDamage;
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (isCrit()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (isCrit()) {
            this.damage *= 1.5;
            this.isDamageModified = true;
        }
    }

    public boolean isCrit()
    {
        if (Wiz.p().hand.size() >= 6)
        return true;

        return false;
    }

    @Override
    public void upp() {
            upgradeDamage(UPGRADE_PLUS_DMG);
    }
}