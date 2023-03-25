package thePackmaster.cards.frostpack;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.MissileStrikeEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;


public class Hailstorm extends AbstractFrostCard {
    public final static String ID = makeID("Hailstorm");

    public Hailstorm() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 5;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = this.magicNumber;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i) {
            Wiz.atb(new AbstractGameAction() {
                @Override
                public void update() {
                    AbstractMonster m2 = Wiz.getRandomEnemy();
                    if(m2 != null) {
                        Wiz.doDmg(m2, damage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT, false, true);
                        Wiz.att(new VFXAction(new MissileStrikeEffect(m2.hb.cX + 210F * Settings.scale, m2.hb.cY + 250F * Settings.scale, MathUtils.random(-80.0F, -100.0F), Color.WHITE)));
                    }
                    isDone = true;
                }
            });
        }

        for (int i = 0; i < this.magicNumber; ++i) {
            Wiz.atb(new ChannelAction(new Frost()));
        }
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        this.showEvokeOrbCount = this.magicNumber;
    }

}


