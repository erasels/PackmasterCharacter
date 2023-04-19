package thePackmaster.patches;

import basemod.ReflectionHacks;
import basemod.patches.com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar.ColorTabBarFix;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch2;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
import com.megacrit.cardcrawl.screens.mainMenu.ColorTabBar;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import thePackmaster.ThePackmaster;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.Wiz;

@SpirePatch2(clz = AbstractCard.class, method = "render", paramtypez = {SpriteBatch.class})
@SpirePatch2(clz = AbstractCard.class, method = "renderInLibrary", paramtypez = {SpriteBatch.class})
public class RenderBaseGameCardPackTopTextPatches {
    @SpirePostfixPatch
    public static void patch(AbstractCard __instance, SpriteBatch sb) {
        if(shouldShowPackName(__instance)) {
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

    public static boolean shouldShowPackName(AbstractCard c) {
        return !Settings.hideCards
                && c.getClass().getSuperclass().equals(AbstractCard.class)
                && (isInPackmasterRun() || isInPackmasterCardLibraryScreen());
    }

    private static boolean isInPackmasterRun() {
        return AbstractDungeon.player != null && AbstractDungeon.player.chosenClass == ThePackmaster.Enums.THE_PACKMASTER;
    }

    public static boolean isInPackmasterCardLibraryScreen() {
        if (!CardCrawlGame.isInARun() && CardCrawlGame.mainMenuScreen.screen == MainMenuScreen.CurScreen.CARD_LIBRARY) {
            ColorTabBar colorBar = ReflectionHacks.getPrivate(CardCrawlGame.mainMenuScreen.cardLibraryScreen, CardLibraryScreen.class, "colorBar");
            if (colorBar.curTab == ColorTabBarFix.Enums.MOD) {
                ColorTabBarFix.ModColorTab modColorTab = ColorTabBarFix.Fields.getModTab();
                return modColorTab != null && modColorTab.color == ThePackmaster.Enums.PACKMASTER_RAINBOW;
            }
        }
        return false;
    }
}
