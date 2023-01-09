package thePackmaster.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

@SpirePatch2(clz = AbstractCard.class, method = "render", paramtypez = {SpriteBatch.class})
public class RenderBaseGameCardPackTopTextPatches {
    @SpirePostfixPatch
    public static void patch(AbstractCard __instance, SpriteBatch sb) {
        if(!Settings.hideCards && AbstractDungeon.player != null &&
                AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER && __instance.getClass().getSuperclass().equals(AbstractCard.class)) {
            AbstractCardPack pack = Wiz.getPackByCard(__instance);
            if (pack != null) {
                float xPos, yPos, offsetY;
                BitmapFont font;
                String text = pack.name;
                if (text == null || __instance.isFlipped || __instance.isLocked || __instance.transparency <= 0.0F)
                    return;
                    font = FontHelper.cardTitleFont;
                    xPos = __instance.current_x;
                    yPos = __instance.current_y;
                    offsetY = 400.0F * Settings.scale * __instance.drawScale / 2.0F;
                BitmapFont.BitmapFontData fontData = font.getData();
                float originalScale = fontData.scaleX;
                float scaleMulti = 0.8F;
                int length = text.length();
                if (length > 20) {
                    scaleMulti -= 0.02F * (length - 20);
                    if (scaleMulti < 0.5F)
                        scaleMulti = 0.5F;
                }
                fontData.setScale(scaleMulti * (__instance.drawScale * 0.85f));
                Color color = AbstractPackmasterCard.packNameDisplayColor.cpy();
                color.a = __instance.transparency;
                FontHelper.renderRotatedText(sb, font, text, xPos, yPos, 0.0F, offsetY, __instance.angle, true, color);
                fontData.setScale(originalScale);
            }
        }
    }
}
