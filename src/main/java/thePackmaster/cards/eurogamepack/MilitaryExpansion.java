package thePackmaster.cards.eurogamepack;

import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnReceivePowerPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.eurogamepack.EntranceTrackerPower;
import thePackmaster.powers.eurogamepack.TotalTrackerPower;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;

public class MilitaryExpansion extends AbstractPackmasterCard {
    public static final String ID = makeID("MilitaryExpansion");

    public MilitaryExpansion() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = 1;
        this.baseSecondMagic = this.secondMagic = 5;
        this.baseDamage = this.damage = 0;
        this.isMultiDamage = true;
        setBackgroundTexture(
                "anniv5Resources/images/512/eurogame/" + type.name().toLowerCase() + ".png",
                "anniv5Resources/images/1024/eurogame/" + type.name().toLowerCase() + ".png"
        );

    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(new CleaveEffect()));
        this.addToBot(new DamageAllEnemiesAction(p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

@Override
    public void calculateCardDamage(AbstractMonster mo) {
    if (AbstractDungeon.player.hasPower(TotalTrackerPower.POWER_ID)) {
        int count = AbstractDungeon.player.getPower(TotalTrackerPower.POWER_ID).amount / secondMagic;
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * count;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    else{
        int realBaseDamage = this.baseDamage;
        super.calculateCardDamage(mo);
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    }
@Override
    public void applyPowers() {
    if (AbstractDungeon.player.hasPower(TotalTrackerPower.POWER_ID)) {
        int count = AbstractDungeon.player.getPower(TotalTrackerPower.POWER_ID).amount / secondMagic;
        int realBaseDamage = this.baseDamage;
        this.baseDamage += this.magicNumber * count;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    else{
        int realBaseDamage = this.baseDamage;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.isDamageModified = this.damage != this.baseDamage;
    }
    }

    public void upp() {
            this.upgradeSecondMagic(-1);
    }
//upgrade by -1 hehe


}
