package thePackmaster.patches.strikespack;

import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.powers.strikepack.StrikeABargainPower;

@SpirePatch(clz = AbstractCard.class, method = "freeToPlay")
public class StrikeABargainFreePatch {
    public static boolean Postfix(boolean __result, AbstractCard __instance) {
        if (AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                (AbstractDungeon.getCurrRoom()).phase == AbstractRoom.RoomPhase.COMBAT &&
                AbstractDungeon.player.hasPower(StrikeABargainPower.POWER_ID) && __instance.type == AbstractCard.CardType.ATTACK)
            return true;
        return __result;
    }
}

