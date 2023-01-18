package thePackmaster.actions.evenoddpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.StarBounceEffect;
import thePackmaster.powers.evenoddpack.GammaWardPower;
import thePackmaster.powers.evenoddpack.PrimeDirectivePower;
import thePackmaster.util.Wiz;

public class GammaWardAction extends AbstractGameAction {
    
    public GammaWardAction(int amount) {
        this.amount = amount;
    }
    
    public void update() {
        if (AbstractDungeon.actionManager.cardsPlayedThisTurn.size() % 2 == 0) {
            Wiz.applyToSelf(new GammaWardPower(AbstractDungeon.player, amount));
        }
        this.isDone = true;
    }
}
