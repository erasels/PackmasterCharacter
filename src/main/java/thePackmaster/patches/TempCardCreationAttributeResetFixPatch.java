package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.Soul;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

//Required to fix Rummage cost modification being overwritten for Cards added to top of draw pile like Darkside Slap
public class TempCardCreationAttributeResetFixPatch {
    @SpirePatch2(clz = Soul.class, method = SpirePatch.CLASS)
    public static class VisualOnlySoulField {
        public static SpireField<Boolean> visualOnly = new SpireField<>(()->false);
    }

    @SpirePatch2(clz = Soul.class, method = "onToDeck", paramtypez = {AbstractCard.class, boolean.class, boolean.class})
    public static class SetVisualOnlySoul {
        @SpirePrefixPatch
        public static void patch(Soul __instance, boolean visualOnly) {
            if(visualOnly)
                VisualOnlySoulField.visualOnly.set(__instance, true);
        }
    }

    @SpirePatch2(clz = Soul.class, method = "update")
    public static class ResetVisualOnlySoul {
        @SpirePostfixPatch
        public static void patch(Soul __instance) {
            if(__instance.isReadyForReuse) {
                VisualOnlySoulField.visualOnly.set(__instance, false);
            }
        }
    }

    @SpirePatch2(clz = Soul.class, method = "update")
    public static class SkipClearingPowersForVisualOnly {
        @SpireInstrumentPatch
        public static ExprEditor Skipit() {
            return new ExprEditor() {
                @Override
                public void edit(MethodCall m) throws CannotCompileException {
                    if (m.getClassName().equals(AbstractCard.class.getName()) && m.getMethodName().equals("clearPowers")) {
                        m.replace(
                                "if(!" + SkipClearingPowersForVisualOnly.class.getName() + ".isVisual(this)) {" +
                                        "$proceed($$);" +
                                        "}");
                    }
                }
            };
        }

        public static boolean isVisual(Soul soul) {
            return VisualOnlySoulField.visualOnly.get(soul);
        }
    }
}
