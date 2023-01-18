package thePackmaster.actions.evenoddpack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import thePackmaster.util.Wiz;

public class WitzBoltAction  extends AbstractGameAction {
    
    public WitzBoltAction(int amount, AbstractCreature target) {
        this.amount = amount;
        this.target = target;
    }
    
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 1) {
            Wiz.applyToEnemyTop((AbstractMonster) target, new VulnerablePower(target, amount, false));
            Wiz.applyToEnemyTop((AbstractMonster) target, new WeakPower(target, amount, false));
            AbstractDungeon.actionManager.addToBottom(new SFXAction("VO_CHAMP_2A", 0.5f, true));
            for(int i = 0; i <5; i++) {
                addToBot(new VFXAction(new StarBounceEffect(target.hb.cX, target.hb.cY), 0.05F));
            }
        }
        this.isDone = true;
    }
}
