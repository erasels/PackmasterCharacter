package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class ExplosiveVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("ExplosiveVial");

    public ExplosiveVial() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        isMultiDamage = true;
        exhaust = true;
        baseMagicNumber = magicNumber = 2;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!Wiz.getEnemies().isEmpty()) {
            AbstractMonster m2 = Wiz.getEnemies().get(0);

            if (m2 != null) {
                addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("ExplosivePotion.png"), p.hb.cX, p.hb.cY, m2.hb.cX, m2.hb.cY, 3F, 0.6F, false, false), 0.6F));
            }
            allDmg(AbstractGameAction.AttackEffect.FIRE);
            if (upgraded) {
                for (AbstractMonster m3 : Wiz.getEnemies()
                ) {

                    Wiz.applyToEnemy(m3, new IgnitePower(m3, magicNumber));

                }

            }
        }
    }


    public void upp() {
    }
}