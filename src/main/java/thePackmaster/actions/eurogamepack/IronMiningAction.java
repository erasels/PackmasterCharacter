package thePackmaster.actions.eurogamepack;



import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import thePackmaster.powers.eurogamepack.VictoryPoints;

public class IronMiningAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private int VPplus;
    private AbstractMonster targetMonster;

    public IronMiningAction(int VPplus, AbstractMonster m) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.VPplus = VPplus;
        this.targetMonster = m;
    }

    public void update() {
        if (this.targetMonster == null || this.targetMonster.getIntentBaseDmg() >= 0) {

        }
        else {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new VictoryPoints(AbstractDungeon.player, this.VPplus), this.VPplus));
        }
        this.isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("OpeningAction");
        TEXT = uiStrings.TEXT;
    }
}
