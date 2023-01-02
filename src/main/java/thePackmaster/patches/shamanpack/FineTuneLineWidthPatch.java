package thePackmaster.patches.shamanpack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DescriptionLine;
import javassist.CannotCompileException;
import javassist.CtBehavior;
import javassist.expr.ExprEditor;
import javassist.expr.NewExpr;
import thePackmaster.cards.shamanpack.FueledByEmbers;

@SpirePatch(clz = AbstractCard.class, method = "initializeDescription")
public class FineTuneLineWidthPatch {
    public static final float WIDTH_ADJUSTMENT = 20.0f;
    public static boolean addWidthBack = false;

    private static boolean matches(AbstractCard card, String word) {
        return (card.cardID.equals(FueledByEmbers.ID) && word.equals("*Ember"))
                || (card.cardID.equals(FueledByEmbers.ID) && word.equals("pile"));
    }

    @SpireInsertPatch(locator = Locator.class, localvars = {"word", "currentWidth"})
    public static void fineTuneLineWidth(AbstractCard __instance, String word, @ByRef float[] currentWidth) {
        if (matches(__instance, word)) {
            currentWidth[0] = currentWidth[0] - WIDTH_ADJUSTMENT;
            FineTuneLineWidthPatch.addWidthBack = true;
        }
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(String.class, "toLowerCase");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }

    public static class FineTuneLineWidthExprEditor extends ExprEditor {
        @Override
        public void edit(NewExpr newExpr) throws CannotCompileException {
            if (newExpr.getClassName().equals(DescriptionLine.class.getName())) {
                newExpr.replace(String.format("{ if(%1$s.addWidthBack) { $2 += %1$s.WIDTH_ADJUSTMENT; %1$s.addWidthBack = false; }; $_ = $proceed($$); }", FineTuneLineWidthPatch.class.getName()));
            }
        }
    }

    @SpireInstrumentPatch
    public static ExprEditor fineTuneLineWidthPatch() {
        return new FineTuneLineWidthExprEditor();
    }
}
