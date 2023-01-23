package thePackmaster.util.dynamicdynamic;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;

import java.util.HashMap;

public class DynamicDynamicVariableManager {
    public static HashMap<String, DynamicDynamicVariable> variableDatabase = new HashMap<>();

    public static void clearVariables() {
        for (String id : variableDatabase.keySet()) {
            BaseMod.cardDynamicVariableMap.remove(id);
        }
        variableDatabase.clear();
    }

    public static void registerVariable(AbstractCard card, DynamicProvider mod) {
        String id = generateKey(card, mod);
        if (!variableDatabase.containsKey(id)) {
            DynamicDynamicVariable variable = new DynamicDynamicVariable(id, mod);
            variableDatabase.put(id, variable);
            BaseMod.cardDynamicVariableMap.put(id, variable);
        }
    }

    public static String generateKey(AbstractCard card, DynamicProvider mod) {
        return SpireAnniversary5Mod.makeID(card.uuid + ":" + mod.getDynamicUUID());
    }
}
