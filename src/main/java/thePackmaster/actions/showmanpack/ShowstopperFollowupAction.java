package thePackmaster.actions.showmanpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.GrandFinalEffect;
import thePackmaster.util.Wiz;

public class ShowstopperFollowupAction extends AbstractGameAction {

    int showStopperDamage;
    AbstractPlayer player;
    int blockThreshold;
    public ShowstopperFollowupAction(AbstractPlayer p, int baseDamage, int threshold) {
        this.showStopperDamage = baseDamage;
        this.actionType = ActionType.DAMAGE;
        this.player = p;
        this.blockThreshold = threshold;
    }

    public void update() {
        if (player.currentBlock >= blockThreshold){
            addToBot(new VFXAction(new GrandFinalEffect(), 0.4f));
            for (AbstractMonster mo : Wiz.getEnemies()){
                addToBot(new LoseHPAction(mo, player, showStopperDamage, AttackEffect.FIRE));
            }
        }
        this.isDone = true;
    }
}
