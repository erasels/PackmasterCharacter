package thePackmaster.actions.intothebreachpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static com.megacrit.cardcrawl.core.CardCrawlGame.sound;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.effectsQueue;

public class VoidShockAction extends AbstractGameAction {
    AbstractPower callingPower;
    DamageInfo info;

    public VoidShockAction(AbstractCreature target, AbstractPower voidShockPower, DamageInfo info) {
        this.target = target;
        this.callingPower = voidShockPower;
        this.info = info;
    }

    public void update() {
        callingPower.flash();
        effectsQueue.add(new LightningEffect(target.hb.cX, target.hb.cY));
        sound.play("ORB_LIGHTNING_EVOKE", 0.5F);
        target.damage(info);
        this.isDone = true;
    }
}