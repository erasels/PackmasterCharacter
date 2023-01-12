package thePackmaster.actions.distortionpack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.patches.BetterPowerNegationCheckPatch;
import thePackmaster.vfx.distortionpack.ImproveEffect;

public class ImproveAction extends AbstractGameAction {
    private AbstractMonster materia;
    private ApplyPowerAction distortionApplier;

    public ImproveAction(AbstractMonster m, int power, ApplyPowerAction distortionApplier) {
        this.actionType = ActionType.SPECIAL;
        this.materia = m;
        this.amount = Math.min(15, power);
        this.distortionApplier = distortionApplier;
    }
    public ImproveAction(AbstractMonster m, ApplyPowerAction distortionApplier) {
        this(m, 10, distortionApplier);
    }

    public ImproveAction(AbstractMonster m) {
        this(m, null);
    }

    public void update() {
        this.isDone = true;
        if (materia == null || (distortionApplier != null && !BetterPowerNegationCheckPatch.Field.appliedSuccess.get(distortionApplier))) {
            return;
        }

        for (AbstractGameEffect e : AbstractDungeon.effectList) {
            if (e instanceof ImproveEffect) {
                if (materia.equals(((ImproveEffect) e).m))
                    return;
            }
        }

        AbstractDungeon.effectList.add(new ImproveEffect(materia, amount));
    }
}