package thePackmaster.cards.batterpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.vfx.arcanapack.SunBeamEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class UltimateHomerun extends AbstractBatterCard {
    public final static String ID = makeID("UltimateHomerun");

    private static final int DAMAGE = 0;
    public static int HIGH_SCORE = 0;

    public UltimateHomerun() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.baseDamage = HIGH_SCORE;
        this.calculateCardDamage(m);

        // Adjust visual intensity based on power.
        if (this.damage < 15)
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        else if (this.damage < 100)
            AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        else
        {
            dmg(m, AbstractGameAction.AttackEffect.FIRE);
            AbstractDungeon.effectsQueue.add(new SunBeamEffect(m));
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo)
    {
        int tmp = baseDamage;
        this.baseDamage = HIGH_SCORE;
        super.calculateCardDamage(mo);
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;
    }

    public void applyPowers() {
        int tmp = baseDamage;
        this.baseDamage = HIGH_SCORE;
        super.applyPowers();
        if(tmp != baseDamage) isDamageModified = true;
        baseDamage = tmp;

        if (HIGH_SCORE >= 100)
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[1];
        else
            this.rawDescription = cardStrings.DESCRIPTION + cardStrings.EXTENDED_DESCRIPTION[0];

        this.initializeDescription();
    }


    @Override
    public void upp() {
        upgradeBaseCost(2);
    }
}