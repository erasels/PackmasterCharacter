package thePackmaster.cards.clawpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import thePackmaster.actions.WaitMoreAction;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class TwinClaw extends AbstractClawCard {
    public final static String ID = makeID("TwinClaw");

    public TwinClaw() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 2;
        tags.add(CLAW);

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.FIREBRICK, Color.WHITE), 0.1F));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
            addToBot(new WaitMoreAction(0.2F));
            this.addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.RED, Color.WHITE), 0.1F));
            this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        }

        ClawUp(magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}