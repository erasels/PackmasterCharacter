package thePackmaster.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireInstrumentPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.random.Random;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FilterOutPlaceholderPotionsPatch {
    @SpirePatch(clz = PotionHelper.class, method = "getRandomPotion", paramtypez = {})
    @SpirePatch(clz = PotionHelper.class, method = "getRandomPotion", paramtypez = {Random.class})
    public static class FilterOutPlaceholderPotionsExprEditor extends ExprEditor {
        @Override
        public void edit(FieldAccess fieldAccess) throws CannotCompileException {
            if (fieldAccess.getClassName().equals(PotionHelper.class.getName()) && fieldAccess.getFieldName().equals("potions")) {
                fieldAccess.replace(String.format("{ $_ = %1$s.filterOutPlaceholderPotions(potions); }", FilterOutPlaceholderPotionsPatch.class.getName()));
            }
        }

        @SpireInstrumentPatch
        public static ExprEditor filterOutPlaceholderRarityPotions() {
            return new FilterOutPlaceholderPotionsExprEditor();
        }
    }

    public static ArrayList<String> filterOutPlaceholderPotions(ArrayList<String> potions) {
        return new ArrayList<>(potions).stream()
            .filter(potionId -> PotionHelper.getPotion(potionId).rarity != AbstractPotion.PotionRarity.PLACEHOLDER)
            .collect(Collectors.toCollection(ArrayList::new));
    }
}