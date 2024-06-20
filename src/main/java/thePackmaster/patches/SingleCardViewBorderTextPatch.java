package thePackmaster.patches;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

import java.lang.reflect.Field;

@SpirePatch(clz=SingleCardViewPopup.class, method="renderTitle")
public class SingleCardViewBorderTextPatch {
    private static Field cardField;
    private static Field yField;
    private static float drawScale = 2.0F;
    private static float yOffsetBase = 690;

    static {
        try {
            cardField = SingleCardViewPopup.class.getDeclaredField("card");
            cardField.setAccessible(true);
            yField = SingleCardViewPopup.class.getDeclaredField("current_y");
            yField.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Postfix(SingleCardViewPopup __instance, SpriteBatch sb) {
        try {
            AbstractCard card = (AbstractCard)cardField.get(__instance);
            if (card instanceof AbstractPackmasterCard) {
                ((AbstractPackmasterCard)card).renderBorderText(sb, Settings.WIDTH / 2.0F, (float)yField.get(__instance), yOffsetBase, drawScale);
            } else if (RenderBaseGameCardPackTopTextPatches.shouldShowPackName(card)) {
                AbstractCardPack pack = Wiz.getPackByCard(card);
                if (pack != null) {
                    float xPos, yPos, offsetY;
                    BitmapFont font;
                    String text = pack.name;
                    if (text == null || card.isFlipped || card.isLocked || card.transparency <= 0.0F)
                        return;
                        font = FontHelper.cardTitleFont;
                        xPos = Settings.WIDTH / 2.0F;
                        yPos = (float)yField.get(__instance);
                        offsetY = yOffsetBase * Settings.scale * drawScale / 2.0F;
                    BitmapFont.BitmapFontData fontData = font.getData();
                    float originalScale = fontData.scaleX;
                    float scaleMulti = 0.8F;
                    int length = text.length();
                    if (length > 20) {
                        scaleMulti -= 0.02F * (length - 20);
                        if (scaleMulti < 0.5F)
                            scaleMulti = 0.5F;
                    }
                    fontData.setScale(scaleMulti * (drawScale * 0.85f));
                    Color color = AbstractPackmasterCard.packNameDisplayColor.cpy();
                    color.a = card.transparency;
                    FontHelper.renderRotatedText(sb, font, text, xPos, yPos, 0.0F, offsetY, card.angle, true, color);
                    fontData.setScale(originalScale);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}