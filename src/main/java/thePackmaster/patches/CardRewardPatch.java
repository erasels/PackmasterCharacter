package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.CombatRewardScreen;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.powers.strikepack.StrikeABargainPower;

import java.util.ArrayList;

@SpirePatch(clz = CombatRewardScreen.class, method = "setupItemReward")
public class CardRewardPatch {
    @SpirePrefixPatch
    public static SpireReturn<?> Prefix(CombatRewardScreen __instance) {
        if (SpireAnniversary5Mod.skipDefaultCardRewards) {
            __instance.rewards = new ArrayList<>(AbstractDungeon.getCurrRoom().rewards);
            ReflectionHacks.setPrivate(__instance, CombatRewardScreen.class, "rewardAnimTimer", 0.2F);
            AbstractDungeon.overlayMenu.proceedButton.show();
            InputHelper.justClickedLeft = false;
            __instance.hasTakenAll = false;
            __instance.positionRewards();
            return SpireReturn.Return();
        } else {
            return SpireReturn.Continue();
        }
    }
}

