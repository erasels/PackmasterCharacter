package thePackmaster.actions.summonerspellspack;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.MinionPower;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

public class SmitingStrikeAction extends AbstractGameAction {

    public SmitingStrikeAction() {
        this.actionType = ActionType.WAIT;
    }


    public SmitingStrikeAction(int damage, AbstractCreature target, AbstractCreature source) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.source = source;
        this.target = target;
        this.amount = damage;
    }

    public void update() {
        this.addToBot(new SFXAction("ORB_LIGHTNING_EVOKE"));
        this.addToBot(new VFXAction(new LightningEffect(target.drawX, target.drawY), 0.2f));
        target.tint.color.set(Color.ORANGE);
        target.tint.changeColor(Color.WHITE.cpy());

        if (target.hasPower(MinionPower.POWER_ID))
            this.addToBot(new InstantKillAction(this.target));
        else
            addToBot(new DamageAction(target, new DamageInfo(source, amount, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.NONE));

        this.isDone = true;
    }
}