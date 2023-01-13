package thePackmaster.cards.clawpack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class GhostClaw extends AbstractClawCard {
    public final static String ID = makeID("GhostClaw");

    public GhostClaw() {
        super(ID, 0, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = 3;
        baseMagicNumber = magicNumber = 2;
        tags.add(CLAW);
        exhaust = true;

    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m != null) {
            this.addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.WHITE, Color.WHITE), 0.1F));
        }
        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        ClawUp(magicNumber);
    }

    public void upp() {
        upgradeDamage(2);
    }
}