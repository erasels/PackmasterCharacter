package thePackmaster.patches.warlockpack;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import thePackmaster.cards.warlockpack.Imp;

@SpirePatch(clz = AbstractPlayer.class, method = "updateSingleTargetInput")
@SpirePatch(clz = AbstractPlayer.class, method = "clickAndDragCards")
@SpirePatch(clz = AbstractPlayer.class, method = "releaseCard")
@SpirePatch(clz = CardGroup.class, method = "glowCheck")
public class ImpPreventPlayOrGlowInHandPatch {
    public static class ImpDontGlowInHandExprEditor extends ExprEditor {
        @Override
        public void edit(MethodCall methodCall) throws CannotCompileException {
            if (methodCall.getClassName().equals(AbstractCard.class.getName()) && methodCall.getMethodName().equals("canUse")) {
                methodCall.replace(String.format("{ $_ = !($0 instanceof %1$s) && $proceed($$); }", Imp.class.getName()));
            }
        }
    }

    @SpireInstrumentPatch
    public static ExprEditor impDontGlowInHandPatch() {
        return new ImpDontGlowInHandExprEditor();
    }
}
