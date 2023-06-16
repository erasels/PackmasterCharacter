package thePackmaster.patches.lockonpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.defect.LightningOrbEvokeAction;
import com.megacrit.cardcrawl.actions.defect.LightningOrbPassiveAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.Lightning;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.powers.lockonpack.LightningRodPower;
import thePackmaster.util.Wiz;

import java.util.ArrayList;
import java.util.stream.Collectors;

@SpirePatch2(clz = Lightning.class, method = "triggerPassiveEffect")
@SpirePatch2(clz = LightningOrbPassiveAction.class, method = "update")
@SpirePatch2(clz = LightningOrbEvokeAction.class, method = "update")
public class ActualLockOnPatch {
    public static AbstractMonster getLockedMonster(AbstractMonster originalTarget)
    {
        if (originalTarget == null || originalTarget.hasPower(LightningRodPower.POWER_ID)) return originalTarget;

        ArrayList<AbstractMonster> lockedList = Wiz.getEnemies().stream().
                filter(m -> m.hasPower(LightningRodPower.POWER_ID)).collect(Collectors.toCollection(ArrayList::new));

        if (lockedList.size() == 0) return originalTarget;
        return lockedList.get(0);
    }
    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                if (!m.getMethodName().equals("getRandomMonster")) return;
                m.replace("$_ = " + ActualLockOnPatch.class.getName() + ".getLockedMonster($proceed($$));");
            }
        };
    }
}
