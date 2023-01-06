package thePackmaster.cardmodifiers.transmutationpack.dynamicdynamic;

import basemod.BaseMod;
import com.megacrit.cardcrawl.cards.AbstractCard;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cardmodifiers.transmutationpack.AbstractExtraEffectModifier;

import java.util.HashMap;

public class DynamicDynamicVariableManager {
    public static HashMap<String, DynamicDynamicVariable> variableDatabase = new HashMap<>();

    public static void clearVariables() {
        for (String id : variableDatabase.keySet()) {
            BaseMod.cardDynamicVariableMap.remove(id);
        }
        variableDatabase.clear();
    }

    public static void registerVariable(AbstractCard card, AbstractExtraEffectModifier mod) {
        String id = generateKey(card, mod);
        if (!variableDatabase.containsKey(id)) {
            DynamicDynamicVariable variable = new DynamicDynamicVariable(id, mod);
            variableDatabase.put(id, variable);
            BaseMod.cardDynamicVariableMap.put(id, variable);
        }
    }

    public static String generateKey(AbstractCard card, AbstractExtraEffectModifier mod) {
        return SpireAnniversary5Mod.makeID(card.uuid + ":" + mod.attachedCard.uuid);
    }
}
