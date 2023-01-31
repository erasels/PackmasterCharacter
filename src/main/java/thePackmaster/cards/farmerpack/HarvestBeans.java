package thePackmaster.cards.farmerpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.p;

public class HarvestBeans extends AbstractFarmerCard {
    public final static String ID = makeID("HarvestBeans");

    public HarvestBeans() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 5;
        baseSecondDamage = secondDamage = 5;
        this.isMultiDamage = true;
    }
    public void triggerOnGlowCheck() {
        if (!ptc().isEmpty() && ptc().get(ptc().size() - 1).type != CardType.ATTACK) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else {
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public void applyPowers() {
        int origBase = this.baseDamage;

        this.baseDamage = baseSecondDamage;
        this.isMultiDamage = true;
        super.applyPowers();

        int aoeDmg = this.damage;
        boolean aoeModified = this.isDamageModified;

        this.baseDamage = origBase;
        this.isMultiDamage = false;
        super.applyPowers();

        //recalcing also resets variables and auto recalculates these...
        this.secondDamage = aoeDmg;
        this.isSecondDamageModified = aoeModified;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int origBase = this.baseDamage;

        this.baseDamage = baseSecondDamage;
        this.isMultiDamage = true;
        super.calculateCardDamage(null);

        int aoeDmg = this.damage;
        boolean aoeModified = this.isDamageModified;

        this.baseDamage = origBase;
        this.isMultiDamage = false;
        super.calculateCardDamage(mo);

        this.secondDamage = aoeDmg;
        this.isSecondDamageModified = aoeModified;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        if (ptc().size() >= 2 && ptc().get(ptc().size() - 2).type != CardType.ATTACK) {
            atb(new DamageAllEnemiesAction(p(), multiDamage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }

    public void upp() {
        upgradeDamage(2);
        upgradeSecondDamage(2);
    }

    private ArrayList<AbstractCard> ptc() {
        return AbstractDungeon.actionManager.cardsPlayedThisCombat;
    }
}
