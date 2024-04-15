package thePackmaster.cards.showmanpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.RainbowCardEffect;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class Dazzle extends AbstractShowmanCard {
    public final static String ID = makeID("Dazzle");

    public Dazzle() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 4;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new RainbowCardEffect(), 0.1f));
        for(int i = 0; i < this.damage; ++i) {
            AbstractDungeon.effectsQueue.add(new StarBounceEffect(m.hb.cX, m.hb.cY));
        }
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));
        this.addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false)));
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeDamage(2);
    }
}