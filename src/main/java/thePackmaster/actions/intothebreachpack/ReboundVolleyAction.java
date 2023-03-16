package thePackmaster.actions.intothebreachpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.intothebreachpack.ReboundVolley;
import thePackmaster.util.Wiz;
import thePackmaster.vfx.womaninbluepack.PotionThrowEffect;

import java.util.ArrayList;
import java.util.Collections;

import static thePackmaster.util.Wiz.*;

public class ReboundVolleyAction extends AbstractGameAction {
    private ReboundVolley card;
    private AbstractMonster targetEnemy;
    private int targets;
    private ArrayList<AbstractMonster> canTarget;
    private ArrayList<AbstractGameAction> actionsHoldingQueue;
    private float currentX;
    private float currentY;

    public ReboundVolleyAction(ReboundVolley card, AbstractMonster specifiedTarget, int randomTargets) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.card = card;
        this.targetEnemy = specifiedTarget;
        this.targets = randomTargets+1;
        this.canTarget = getEnemies();
        this.actionsHoldingQueue = new ArrayList<>();
        this.currentX = adp().hb.cX;
        this.currentY = adp().hb.cY;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            boolean firstHit = true;
            for (int i=0; i<targets; i++) {
                if (canTarget.isEmpty()) {
                    tickDuration();
                    return;
                }
                if (!canTarget.contains(targetEnemy)) {
                    targetEnemy = Wiz.getRandomItem(canTarget);
                }
                canTarget.remove(targetEnemy);
                if (targetEnemy != null) {
                    actionsHoldingQueue.add(new VFXAction(
                            new PotionThrowEffect(
                                    SpireAnniversary5Mod.makeImagePath("vfx/Cannonball.png"),
                                    currentX, currentY, targetEnemy.hb.cX, targetEnemy.hb.cY,
                                    1.5F, 0.6F, true, true), 0.6F
                            ));
                    this.currentX = targetEnemy.hb.cX;
                    this.currentY = targetEnemy.hb.cY;
                    card.superCalculateCardDamage(targetEnemy);
                    actionsHoldingQueue.add(new DamageAction(targetEnemy,
                            new DamageInfo(adp(), firstHit ? this.card.damage : this.card.secondDamage, this.card.damageTypeForTurn),
                            firstHit ? AttackEffect.BLUNT_LIGHT : AttackEffect.BLUNT_HEAVY));
                    firstHit = false;
                }
            }
            tickDuration();
        } else {
            Collections.reverse(actionsHoldingQueue);
            for (AbstractGameAction action : actionsHoldingQueue) {
                att(action);
            }
            this.isDone = true;
        }
    }
}