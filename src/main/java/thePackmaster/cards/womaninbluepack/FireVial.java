package thePackmaster.cards.womaninbluepack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.shamanpack.IgnitePower;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class FireVial extends AbstractWomanInBlueCard {
    public final static String ID = makeID("FireVial");

    public FireVial() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 20;
        isMultiDamage = true;
        exhaust = true;
        baseMagicNumber = magicNumber = 5;
    }


    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new PotionThrowEffect(getPotionResourcePath("FirePotion.png"), p.hb.cX, p.hb.cY, m.hb.cX, m.hb.cY, 3F, 0.6F, false, false), 0.6F));
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        if (upgraded){
            Wiz.applyToEnemy(m, new IgnitePower(m, magicNumber));
        }
    }


    public void upp() {
    }
}