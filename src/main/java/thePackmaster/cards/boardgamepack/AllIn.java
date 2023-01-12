package thePackmaster.cards.boardgamepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.powers.boardgamepack.DicePower;
import thePackmaster.powers.boardgamepack.SkipNextTurnPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AllIn extends AbstractBoardCard {
    public final static String ID = makeID(AllIn.class.getSimpleName());

    public AllIn() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        damage = baseDamage = 30;
        magicNumber = baseMagicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
        addToBot(new WaitAction(0.8F));
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new GainEnergyAction(upgraded?4:3));
        Wiz.applyToSelf(new SkipNextTurnPower(p, 1));
    }

    public void upp() {
        upgradeDamage(10);
        upgradeMagicNumber(1);
//        uDesc();
    }
}