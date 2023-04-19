package thePackmaster.patches.quietpack;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

@SpirePatch2(
        cls = "silentBackflip.ThingActionFuck",
        method = "update",
        requiredModId = "backflip"
)
public class BackflipFrontflipPatch
{
    public static ExprEditor Instrument()
    {
        return new ExprEditor() {
            @Override
            public void edit(FieldAccess f) throws CannotCompileException
            {
                if (f.isWriter() && f.getFieldName().equals("ratio")) {
                    f.replace(
                            "if (" + BackflipFrontflipPatch.class.getName() + ".isFrontflip(this)) {" +
                                    "$1 *= -1f;" +
                                    "}" +
                                    "$proceed($$);"
                    );
                }
            }
        };
    }

    public static boolean isFrontflip(AbstractGameAction action)
    {
        return Field.isFrontflip.get(action);
    }

    @SpirePatch2(
            cls = "silentBackflip.ThingActionFuck",
            method = SpirePatch.CLASS,
            requiredModId = "backflip"
    )
    public static class Field
    {
        public static SpireField<Boolean> isFrontflip = new SpireField<>(() -> false);
    }
}
