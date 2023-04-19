package thePackmaster.ui;

import basemod.patches.com.megacrit.cardcrawl.screens.options.DropdownMenu.DropdownColoring;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.patches.MainMenuUIPatch;
import thePackmaster.ui.FixedModLabeledToggleButton.FixedModLabeledToggleButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class PackFilterMenu {

    public boolean isOpen = false;

    private final DropdownMenu dropdown;
    private AbstractCardPack viewedPack;
    private AbstractCard previewCard;
    private FixedModLabeledToggleButton checkbox;
    private final ArrayList<AbstractCardPack> packs = new ArrayList<>();


    private static final String[] TEXT = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("PackFilterMenu")).TEXT;
    private static final TextureRegion MENU_BG = new TextureRegion(ImageMaster.loadImage("img/ModPanelBg.png"));

    //positions
    private static final float BG_X_SCALE = Settings.xScale * 0.31f;
    private static final float BG_Y_SCALE = Settings.yScale * 0.8f;
    private static final float BG_X = 25f * Settings.xScale;
    private static final float BG_Y = Settings.HEIGHT - 40f * Settings.yScale - MENU_BG.getRegionHeight() * BG_Y_SCALE;
    private static final float DROPDOWN_X = 90f * Settings.xScale;
    private static final float DROPDOWN_Y = Settings.HEIGHT - 160f * Settings.yScale;
    private static final float CHECKBOX_X = 150f;
    private static final float CHECKBOX_Y = 490f;
    private static final float PREVIEW_X = 235f * Settings.xScale;
    private static final float PREVIEW_Y = 700f * Settings.yScale;

    private static final Color DISABLED_COLOR = Settings.RED_TEXT_COLOR.cpy();

    public PackFilterMenu() {
        ArrayList<String> optionNames = new ArrayList<>();
        List<AbstractCardPack> sortedPacks = new ArrayList<>(SpireAnniversary5Mod.unfilteredAllPacks);
        sortedPacks.sort(Comparator.comparing((pack) -> pack.name));
        for (AbstractCardPack pack : sortedPacks) {
            packs.add(pack);
            optionNames.add(pack.name);
        }

        dropdown = new DropdownMenu(((dropdownMenu, index, s) -> setViewedPack(index)),
                optionNames, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
        DropdownColoring.RowToColor.function.set(dropdown, (index) -> getFilterConfig(packs.get(index).packID) ? null : DISABLED_COLOR);

        checkbox = new FixedModLabeledToggleButton(TEXT[0], CHECKBOX_X, CHECKBOX_Y, Color.WHITE, FontHelper.tipBodyFont, true, null, (label) -> {
        },
                (button) -> clickCheckmark(button.enabled));

        setViewedPack(0);
    }

    public void toggle() {
        if (isOpen) {
            close();
        } else {
            open();
        }
    }

    private void open() {
        isOpen = true;
        if (previewCard != null)
            previewCard.stopGlowing();
    }

    private void close() {
        isOpen = false;
    }

    public void setViewedPack(int index) {
        viewedPack = packs.get(index);
        previewCard = viewedPack.previewPackCard;
        previewCard.stopGlowing();
        checkbox.toggle.enabled = getFilterConfig(viewedPack.packID);
    }

    public void clickCheckmark(boolean wasUnticked) {
        setFilterConfig(viewedPack.packID, wasUnticked);
        updateFilter();
    }

    public void updateFilter() {
        SpireAnniversary5Mod.allPacks.clear();
        for (AbstractCardPack pack : SpireAnniversary5Mod.unfilteredAllPacks) {
            if (getFilterConfig(pack.packID)) {
                SpireAnniversary5Mod.allPacks.add(pack);
            }
        }
    }

    public void update() {
        dropdown.update();
        if (!dropdown.isOpen) {
            checkbox.update();
        }
        previewCard.stopGlowing();
        previewCard.update();
        previewCard.current_x = PREVIEW_X;
        previewCard.current_y = PREVIEW_Y;
        previewCard.hb.move(previewCard.current_x, previewCard.current_y);
        previewCard.hb.update();
        if (viewedPack.credits != null && !MainMenuUIPatch.hatMenu.isOpen) {
            TipHelper.renderGenericTip(
                    previewCard.current_x + previewCard.hb.width,
                    previewCard.current_y + previewCard.hb.height,
                    viewedPack.creditsHeader, viewedPack.credits);
        }
        FontHelper.cardTitleFont.getData().setScale(1f);
    }

    public void render(SpriteBatch sb) {
        sb.draw(MENU_BG, BG_X, BG_Y, 0f, 0f, MENU_BG.getRegionWidth(), MENU_BG.getRegionHeight(), BG_X_SCALE, BG_Y_SCALE, 0f);

        checkbox.render(sb);
        previewCard.render(sb);
        dropdown.render(sb, DROPDOWN_X, DROPDOWN_Y);
    }

    //config stuff

    private static final SpireConfig filterConfig = makeFilterConfig();

    private static SpireConfig makeFilterConfig() {
        try {
            SpireConfig spireConfig = new SpireConfig(SpireAnniversary5Mod.modID, "filterConfig");// 89
            spireConfig.load();
            return spireConfig;
        } catch (IOException var1) {
            var1.printStackTrace();
            return null;
        }
    }

    private static void setFilterConfig(String packID, boolean enabled) {
        if (filterConfig != null) {
            filterConfig.setBool(packID, enabled);
            try {
                filterConfig.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            SpireAnniversary5Mod.logger.info("Error in the Packmaster Pack filter menu : Config wasn't initialized.");
        }
    }

    public static boolean getFilterConfig(String packID) {
        if (filterConfig != null) {
            if (filterConfig.has(packID)) {
                return filterConfig.getBool(packID);
            } else {
                return true;
            }
        }
        SpireAnniversary5Mod.logger.info("Error in the Packmaster Pack filter menu : Config wasn't initialized.");
        return true;
    }


}
