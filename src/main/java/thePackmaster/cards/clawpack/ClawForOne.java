package thePackmaster.cards.clawpack;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ClawEffect;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.CLAW;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ClawForOne extends AbstractClawCard {
    public final static String ID = makeID("ClawForOne");

    public ClawForOne() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        tags.add(CLAW);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        this.addToBot(new VFXAction(new ClawEffect(m.hb.cX, m.hb.cY, Color.ORANGE, Color.WHITE), 0.1F));

        this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        Wiz.atb(new AbstractGameAction() {
            @Override
            public void update() {
                this.isDone = true;
                addToBot(new MoveCardsAction(p.hand, p.discardPile, (c) -> {
                    return (c.hasTag(CLAW) && c != ClawForOne.this);
                }, BaseMod.MAX_HAND_SIZE));
            }
        });
    }

    public void upp() {
        this.upgradeDamage(4);
    }
}