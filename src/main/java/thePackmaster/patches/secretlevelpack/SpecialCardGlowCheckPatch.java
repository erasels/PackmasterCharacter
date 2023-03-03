package thePackmaster.patches.secretlevelpack;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import thePackmaster.powers.secretlevelpack.AchievementHunterPower;

@SpirePatch(
        clz = CardGroup.class,
        method = "triggerOnOtherCardPlayed"
)
public class SpecialCardGlowCheckPatch {
    private static Color blueBorderGlowColor;
    public static boolean playedGlowingCardThisTurn = false;

    public static void Prefix(CardGroup __instance, AbstractCard abstractCard) {
        if (blueBorderGlowColor == null) {
            blueBorderGlowColor = ReflectionHacks.getPrivateStatic(AbstractCard.class, "BLUE_BORDER_GLOW_COLOR");
        }
        if (abstractCard.glowColor != blueBorderGlowColor) {
            playedGlowingCardThisTurn = true;
            AbstractPower p = AbstractDungeon.player.getPower(AchievementHunterPower.POWER_ID);
            if (p != null) {
                p.onSpecificTrigger();
            }
        }
    }
}