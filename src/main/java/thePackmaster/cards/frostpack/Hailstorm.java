package thePackmaster.cards.frostpack;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Frost;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.marisapack.MissileStrikeEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.util.Wiz.atb;


public class Hailstorm extends AbstractFrostCard {
    public final static String ID = makeID("Hailstorm");

    public Hailstorm() {
        super(ID, 3, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 2;
        baseMagicNumber = magicNumber = 5;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; ++i) {
            AbstractMonster m2 = Wiz.getRandomEnemy();
            Wiz.vfx(new MissileStrikeEffect(m2.hb.cX + 210F * Settings.scale, m2.hb.cY + 250F * Settings.scale, MathUtils.random(-80.0F, -100.0F), Color.WHITE));
            Wiz.doDmg(m2, damage, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        }
        for (int i = 0; i < this.magicNumber; ++i) {
            atb(new ChannelAction(new Frost()));
        }


    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

}


