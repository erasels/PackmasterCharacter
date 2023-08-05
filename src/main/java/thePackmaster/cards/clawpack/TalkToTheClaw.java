package thePackmaster.cards.clawpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import thePackmaster.powers.clawpack.ReturnBlockOnClawPower;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TalkToTheClaw extends AbstractClawCard {
    public final static String ID = makeID("TalkToTheClaw");

    public TalkToTheClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 5;
        baseMagicNumber = magicNumber = 2;
        tags.add(CLAW);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.BLUE, Color.WHITE), 0.1F));

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        this.addToBot(new ApplyPowerAction(m, p, new ReturnBlockOnClawPower(m, this.magicNumber), this.magicNumber));
    }

    public void upp() {
        this.upgradeDamage(2);
        this.upgradeMagicNumber(1);
    }
}