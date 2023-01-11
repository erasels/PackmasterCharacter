package thePackmaster.patches.warlockpack;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;
import thePackmaster.cards.warlockpack.Imp;

@SpirePatch(clz = AbstractCard.class, method = "canUse", paramtypez = {AbstractPlayer.class, AbstractMonster.class })
public class ImpCanUsePatch {
    public static class ImpCanUseExprEditor extends ExprEditor {
        @Override
        public void edit(FieldAccess fieldAccess) throws CannotCompileException {
            if (fieldAccess.getClassName().equals(AbstractCard.class.getName()) && fieldAccess.getFieldName().equals("costForTurn")) {
                fieldAccess.replace(String.format("{ $_ = $0 instanceof %1$s ? 0 : $proceed($$); }", Imp.class.getName()));
            }
        }
    }

    @SpireInstrumentPatch
    public static ExprEditor impCanUsePatch() {
        return new ImpCanUseExprEditor();
    }
}
