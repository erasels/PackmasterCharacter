package thePackmaster.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.buttons.GotItButton;
import thePackmaster.SpireAnniversary5Mod;

public class MainMenuFTUE {
    private static UIStrings STRINGS;

    private GotItButton enableButton;
    private float x, y;
    private static final int W = 622, H = 284;
    private Texture img;

    public boolean isDone = false;

    public MainMenuFTUE(float x, float y, Texture img) {
        if(STRINGS == null) {
            STRINGS = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MainMenuFTUE"));
        }
        this.img = img;
        openScreen(x, y);
    }

    public void openScreen(float x, float y) {
        this.x = x;
        this.y = y;
        enableButton = new GotItButton(x, y);
    }

    public void update() {
        enableButton.update();
        if (enableButton.hb.clicked || CInputActionSet.proceed.isJustPressed()) {
            CInputActionSet.proceed.unpress();
            CardCrawlGame.sound.play("DECK_OPEN");

            isDone = true;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        sb.draw(
                ImageMaster.FTUE,
                x - W / 2f,
                y - H / 2f,
                W / 2f,
                H / 2f,
                W,
                H,
                Settings.scale,
                Settings.scale,
                0f,
                0,
                0,
                W,
                H,
                false,
                false);

        sb.setColor(new Color(1f, 1f, 1f, 0.7f + (MathUtils.cosDeg(System.currentTimeMillis() / 2f % 360) + 1.25f) / 5f));
        enableButton.render(sb);

        FontHelper.renderFontLeftTopAligned(
                sb,
                FontHelper.topPanelInfoFont,
                STRINGS.TEXT[0],
                x - 190f * Settings.scale,
                y + 80f * Settings.scale,
                Settings.GOLD_COLOR);

        FontHelper.renderSmartText(
                sb,
                FontHelper.tipBodyFont,
                STRINGS.TEXT[1],
                x - 250f * Settings.scale,
                y + 20f * Settings.scale,
                450f * Settings.scale,
                26f * Settings.scale,
                Settings.CREAM_COLOR);

        FontHelper.renderFontRightTopAligned(
                sb,
                FontHelper.topPanelInfoFont,
                STRINGS.TEXT[2],
                x + 194f * Settings.scale,
                y - 150f * Settings.scale,
                Settings.GOLD_COLOR);

        //Render texture
        if(img != null) {
            sb.setColor(Color.WHITE);
            sb.draw(img, x - ((W * Settings.xScale) /2f) - img.getWidth(), y - (img.getHeight() / 2f));
        }


        if (Settings.isControllerMode) {
            sb.setColor(Color.WHITE);
            sb.draw(
                    CInputActionSet.proceed.getKeyImg(),
                    enableButton.hb.cX - 32f + 130f * Settings.scale,
                    enableButton.hb.cY - 32f + 2f * Settings.scale,
                    32f,
                    32f,
                    64,
                    64,
                    Settings.scale,
                    Settings.scale,
                    0f,
                    0,
                    0,
                    64,
                    64,
                    false,
                    false);
        }
    }
}

