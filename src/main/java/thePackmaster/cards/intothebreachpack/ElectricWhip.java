package thePackmaster.cards.intothebreachpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ElectricWhip extends IntoTheBreachCard {
    public final static String ID = makeID("ElectricWhip");

    public ElectricWhip() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        damage = baseDamage = 5;
        this.isMultiDamage = true;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ORB_LIGHTNING_PASSIVE"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new ChannelAction(new Lightning()));
    }

    public void upp() {
        upgradeDamage(2);
    }
}