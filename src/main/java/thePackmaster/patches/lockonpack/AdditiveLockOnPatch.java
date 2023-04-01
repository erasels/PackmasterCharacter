package thePackmaster.patches.lockonpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.LockOnPower;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import thePackmaster.powers.lockonpack.TunnelVisionPower;
import thePackmaster.util.Wiz;

public class AdditiveLockOnPatch {

    public static int applyMultiLockOn(AbstractMonster m, int damage)
    {
        float damageModifier = 1.5F
                + 0.25F
                * (Wiz.getLogicalPowerAmount(m, LockOnPower.POWER_ID) - 1);
        return (int)(damage * damageModifier);
    }


    @SpirePatch2(clz = DamageInfo.class, method = "createDamageMatrix", paramtypez = {int.class, boolean.class, boolean.class})
    public static class MultihitLightning
    {
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(FieldAccess f) throws CannotCompileException {
                    if (!f.isWriter() || !f.getFieldName().equals("output")) return;

                    f.replace("$1 = " + AdditiveLockOnPatch.class.getName()
                            + ".applyMultiLockOn((" + AbstractMonster.class.getName() + ")"
                            + AbstractDungeon.class.getName()
                            + ".getMonsters().monsters.get(i), info.base);" +
                            "$proceed($$);");

                }
            };
        }
    }

    @SpirePatch2(clz = AbstractOrb.class, method = "applyLockOn")
    public static class SinglehitLightning {
        public static int Postfix(AbstractCreature target, int dmg, int __result)
        {
            if (!Wiz.adp().hasPower(TunnelVisionPower.POWER_ID) || !target.hasPower(LockOnPower.POWER_ID)) return dmg;

            float damageModifier = 1
                    + (float)dmg/(float)__result
                    + 0.25F
                    * (Wiz.getLogicalPowerAmount(target, LockOnPower.POWER_ID) - 1);
            return (int)(dmg * damageModifier);
        }
    }
}
