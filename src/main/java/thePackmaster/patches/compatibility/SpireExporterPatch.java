package thePackmaster.patches.compatibility;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import thePackmaster.cards.AbstractPackmasterCard;

import java.lang.reflect.Field;

@SpirePatch2(
        requiredModId = "ModStSExporter",
        cls = "sts_exporter.CardExportData",
        method = SpirePatch.CONSTRUCTOR,
        paramtypes = {"sts_exporter.ExportHelper", "com.megacrit.cardcrawl.cards.AbstractCard", "int", "boolean"}
)
public class SpireExporterPatch {
    private static Field cardField;
    private static Field colorField;

    @SpirePostfixPatch
    public static void overrideColorField(Object __instance) {
        try {
            if (cardField == null) {
                cardField = __instance.getClass().getDeclaredField("card");
            }
            Object card = cardField.get(__instance);
            if (card instanceof AbstractPackmasterCard) {
                AbstractPackmasterCard packmasterCard = (AbstractPackmasterCard)card;
                if (colorField == null) {
                    colorField = __instance.getClass().getDeclaredField("color");
                }
                colorField.set(__instance, packmasterCard.getParentName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
