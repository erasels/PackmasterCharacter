package thePackmaster.patches.overwhelmingpack;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import thePackmaster.powers.overwhelmingpack.FreeSkillPower;

@SpirePatch(
        clz = AbstractCard.class,
        method = "freeToPlay"
)
public class FreeSkillPatch {
    @SpirePostfixPatch
    public static boolean makeTheSkillsFree(boolean __result, AbstractCard __instance) {
        if (!__result) {
            return AbstractDungeon.player != null && AbstractDungeon.currMapNode != null &&
                    AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT &&
                    AbstractDungeon.player.hasPower(FreeSkillPower.POWER_ID) && __instance.type == AbstractCard.CardType.SKILL;
        }
        return true;
    }
}
