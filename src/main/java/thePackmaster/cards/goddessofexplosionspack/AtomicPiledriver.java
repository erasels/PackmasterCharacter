package thePackmaster.cards.goddessofexplosionspack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.BorderFlashEffect;

import com.megacrit.cardcrawl.vfx.combat.ExplosionSmallEffect;
import thePackmaster.powers.goddessofexplosionspack.FalloutPower;

import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class AtomicPiledriver extends AbstractGoddessOfExplosionsCard {
    public final static String ID = makeID("AtomicPiledriver");


    private static final int DAMAGE = 22;
    private static final int DAMAGE_UP = 6;
    private static final int MAGIC = 8;
    private static final int MAGIC_UP = 4;


    public AtomicPiledriver() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY, CardColor.COLORLESS);
        baseDamage = DAMAGE;

        magicNumber = baseMagicNumber = MAGIC;
        isEthereal = true;

        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.effectsQueue.add(new BorderFlashEffect(Color.RED, true));
        CardCrawlGame.sound.play("STANCE_ENTER_WRATH");

        Wiz.atb(new VFXAction(new ExplosionSmallEffect(m.hb.cX, m.hb.cY), 0.1F));
        Wiz.atb(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        Wiz.atb(new ApplyPowerAction(m,p, new FalloutPower(m,magicNumber),magicNumber,true));

    }

    @Override
    public void upp() {
        upgradeDamage(DAMAGE_UP);
        upgradeMagicNumber(MAGIC_UP);

    }
}