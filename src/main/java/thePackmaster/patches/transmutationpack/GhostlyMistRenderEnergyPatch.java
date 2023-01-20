package thePackmaster.patches.transmutationpack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.cards.transmutationpack.GhostlyMist;

@SpirePatch2(
        clz = AbstractCard.class,
        method = "renderEnergy",
        paramtypez = { SpriteBatch.class }
)
public class GhostlyMistRenderEnergyPatch {
    public static class GhostlyMistRenderEnergyPatchExprEditor extends ExprEditor {
        @Override
        public void edit(MethodCall methodCall) throws CannotCompileException {
            if (methodCall.getClassName().equals(AbstractCard.class.getName()) && methodCall.getMethodName().equals("freeToPlay")) {
                methodCall.replace(String.format("{ $_ = !(this instanceof %1$s) && $proceed($$); }", GhostlyMist.class.getName()));
            }
        }
    }

    @SpireInstrumentPatch
    public static ExprEditor ghostlyMistRenderEnergyPatch() {
        return new GhostlyMistRenderEnergyPatchExprEditor();
    }
}
