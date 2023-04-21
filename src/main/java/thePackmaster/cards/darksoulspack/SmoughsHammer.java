package thePackmaster.cards.darksoulspack;

import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.tempHp.AddTemporaryHPAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.weaponspack.WeaponMasteryPower;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.*;

public class SmoughsHammer extends AbstractDarkSoulsCard {
    public final static String ID = makeID("SmoughsHammer");
    // intellij stuff attack, enemy, rare, 58, , , , 2, 

    public SmoughsHammer() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = damage = 40;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.atb(new AnimateJumpAction(p));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY, new Color(1.0F, 1.0F, 0.1F, 0.0F))));
        AbstractDungeon.actionManager.addToBottom(new WaitAction(0.8F));

        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new com.megacrit.cardcrawl.cards.DamageInfo(p, damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        Wiz.applyToSelf(new WeakPower(p, 1, false));
        Wiz.applyToSelf(new FrailPower(p, 1, false));
        Wiz.applyToSelf(new VulnerablePower(p, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(5);
        if(magicNumber > 0) {
            upgradeMagicNumber(-1);
        }
    }
}