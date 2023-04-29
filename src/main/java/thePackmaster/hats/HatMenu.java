package thePackmaster.hats;

import basemod.patches.com.megacrit.cardcrawl.screens.options.DropdownMenu.DropdownColoring;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.hats.specialhats.AlignmentHat;
import thePackmaster.hats.specialhats.InstantDeathHat;
import thePackmaster.hats.specialhats.PsychicHat;
import thePackmaster.hats.specialhats.SpecialHat;
import thePackmaster.packs.*;
import thePackmaster.ui.FixedModLabeledToggleButton.FixedModLabeledToggleButton;
import thePackmaster.util.Wiz;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static thePackmaster.hats.Hats.currentHat;

public class HatMenu {
    public boolean isOpen = false;
    public static boolean invalidHatSelected = false;
    public static boolean randomHatMode = false;

    private static DropdownMenu dropdown;
    public static final ArrayList<String> hats = new ArrayList<>();
    public static final ArrayList<String> currentlyUnlockedHats = new ArrayList<>();
    public static final ArrayList<String> currentlyUnlockedRainbows = new ArrayList<>();
    public static final ArrayList<String> currentlyUnseenHats = new ArrayList<>();
    public static final Map<String, SpecialHat> specialHats = new HashMap<>();
    public static final Map<String, Integer> hatsToIndexes = new HashMap<>();

    static {
        specialHats.put(AlignmentPack.ID, new AlignmentHat());
        specialHats.put(PsychicPack.ID, new PsychicHat());
        specialHats.put(InstantDeathPack.ID, new InstantDeathHat());
    }

    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("HatMenu")).TEXT;
    private static final TextureRegion MENU_BG = new TextureRegion(ImageMaster.loadImage("img/ModPanelBg.png"));

    //positions
    private static final float BG_X_SCALE = Settings.xScale * 0.275f;
    private static final float BG_Y_SCALE = Settings.yScale * 0.8f;
    private static final float BG_X = 525f * Settings.xScale;
    private static final float BG_Y = Settings.HEIGHT - 40f * Settings.yScale - MENU_BG.getRegionHeight() * BG_Y_SCALE;
    private static final float FLAVOR_X = BG_X + MENU_BG.getRegionWidth() * BG_X_SCALE * 0.5f;
    private static final float DROPDOWN_X = 586f * Settings.xScale;
    private static final float DROPDOWN_Y = Settings.HEIGHT - 160f * Settings.yScale;
    private static final float PREVIEW_X = BG_X + (210 * Settings.xScale);
    private static final float PREVIEW_Y = BG_Y + (215 * Settings.yScale);
    // We pass these values to the button class, which does its own multiplication by xScale/yScale
    private static final float CHECKBOX_X = BG_X / Settings.xScale + 100;
    private static final float CHECKBOX_Y = BG_Y / Settings.yScale + 45;

    public static AbstractPlayer dummy;

    private static final boolean UNLOCK_ALL_HATS = false;

    public static int currentHatIndex;

    private static final FixedModLabeledToggleButton rainbowButton = makeRainbowButton();
    private static boolean showRainbowButton = false;

    private static final Color LOCKED_COLOR = Color.GRAY.cpy().mul(1f,1f,1f,0.95f);
    private static final Color RAINBOW_UNLOCKED_COLOR = new Color(0f,0.9f,1f,1f);
    private static final Color UNSEEN_COLOR = new Color(1f,1f,0.1f,1f);


    public HatMenu() {
        refreshHatDropdown();
    }

    private static FixedModLabeledToggleButton makeRainbowButton() {
        SpireAnniversary5Mod.isHatRainbow = SpireAnniversary5Mod.wasRainbowLastEnabled();
        return new FixedModLabeledToggleButton(TEXT[11], CHECKBOX_X, CHECKBOX_Y, Color.WHITE, FontHelper.tipBodyFont, SpireAnniversary5Mod.isHatRainbow , null, (label) -> {
        },
                (button) -> {
                    try {
                        clickCheckmark(button.enabled);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void clickCheckmark(boolean newState) throws IOException {
        SpireAnniversary5Mod.isHatRainbow = newState;
        SpireAnniversary5Mod.saveRainbowLastEnabled(newState);
    }

    public static AbstractPlayer getDummy() {
        if (dummy == null) {
            dummy = new ThePackmaster("", ThePackmaster.Enums.THE_PACKMASTER);
            dummy.drawX = PREVIEW_X;
            dummy.drawY = PREVIEW_Y;

            dummy.animX = dummy.animY = 0;
        }
        return dummy;
    }

    public static void refreshHatDropdown() {
        boolean init = false;
        if (dropdown == null) {
            init = true;
        } else {
            dropdown.rows.clear();
        }

        ArrayList<String> optionNames = new ArrayList<>(getHatDropdownStrings());

        dropdown = new DropdownMenu(((dropdownMenu, index, s) -> setCurrentHat(index, s)),
                optionNames, FontHelper.tipBodyFont, Settings.CREAM_COLOR);
        DropdownColoring.RowToColor.function.set(dropdown, HatMenu::getColorFromIndex);

        for (int i = 0; i < hats.size(); i++) {
            hatsToIndexes.put(hats.get(i), i);
        }

        if (init) {
            String lastPickedId = SpireAnniversary5Mod.getLastPickedHatID();
            dropdown.setSelectedIndex(hatsToIndexes.getOrDefault(lastPickedId, 0));
            if (currentlyUnlockedRainbows.contains(lastPickedId)) {
                showRainbowButton = true;
            } else if ("Base".equals(lastPickedId) && currentlyUnlockedRainbows.contains(CoreSetPack.ID)) {
                showRainbowButton = true;
            } else if ("Random".equals(lastPickedId) && !currentlyUnlockedRainbows.isEmpty()) {
                showRainbowButton = true;
            } else {
                showRainbowButton = false;
                if (rainbowButton.toggle.enabled) rainbowButton.toggle.toggle();
            }
        }
    }

    public static ArrayList<String> getHatDropdownStrings() {
        ArrayList<String> unlockedHats = SpireAnniversary5Mod.getUnlockedHats();
        ArrayList<String> unlockedRainbows = SpireAnniversary5Mod.getUnlockedRainbows();
        ArrayList<String> unseenHats = SpireAnniversary5Mod.getUnseenHats();

        ArrayList<String> optionNames = new ArrayList<>();
        optionNames.add(TEXT[0]);
        hats.add("Base");
        optionNames.add(TEXT[9]);
        hats.add("Random");
        ArrayList<AbstractCardPack> sortedPacks = new ArrayList<>(SpireAnniversary5Mod.unfilteredAllPacks);
        sortedPacks.sort(Comparator.comparing(AbstractCardPack::getHatName));
        currentlyUnlockedHats.clear();
        currentlyUnlockedRainbows.clear();
        currentlyUnseenHats.clear();
        for (AbstractCardPack s : sortedPacks) {
            //if (unlockedHats.contains(s.packID)) SpireAnniversary5Mod.logger.info("Hat unlock exists: " + s.packID);
            //if (UNLOCK_ALL_HATS) SpireAnniversary5Mod.logger.info("Unlock All Hats enabled and is unlocking " + s.packID);

            if (Gdx.files.internal(Hats.getImagePathFromHatID(s.packID)).exists() || specialHats.containsKey(s.packID)) {
                if (UNLOCK_ALL_HATS || unlockedHats.contains(s.packID)) {
                    hats.add(s.packID);
                    if (unlockedHats.contains(s.packID)) {
                        currentlyUnlockedHats.add(s.packID);
                    }
                    if (unlockedRainbows.contains(s.packID)) {
                        currentlyUnlockedRainbows.add(s.packID);
                    }
                    optionNames.add(s.getHatName());
                } else {
                    hats.add(s.packID);
                    optionNames.add(s.getHatName());
                }
                if (unseenHats.contains(s.packID)) {
                    currentlyUnseenHats.add(s.packID);
                }
            } else {
                hats.add(s.packID);
                optionNames.add(TEXT[6] + " " + s.getHatName());
            }
        }

        return optionNames;
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
    }

    private void close() {
        isOpen = false;
    }

    public static void randomizeHat() {
        if (rainbowButton.toggle.enabled) {
            currentHat = Wiz.getRandomItem(currentlyUnlockedRainbows);
        } else {
            currentHat = Wiz.getRandomItem(currentlyUnlockedHats);
        }
        Hats.addHat(true, currentHat);
        SpireAnniversary5Mod.logger.info("Randomizer chose hat: " + currentHat);
    }

    public static void setCurrentHat(int index, String name) {
        currentHatIndex = index;
        randomHatMode = false;
        if (currentlyUnseenHats.remove(hats.get(index))) {
            try {
                SpireAnniversary5Mod.saveUnseenHats(currentlyUnseenHats);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (index == 0) {
            invalidHatSelected = false;
            currentHat = null;
            //SpireAnniversary5Mod.logger.info("Removing hat.");
            Hats.removeHat(false);
            flavorText = "";
            if (currentlyUnlockedRainbows.contains(CoreSetPack.ID)) {
                showRainbowButton = true;
            }
        } else if (index == 1) {
            currentHat = null;
            if (currentlyUnlockedHats.isEmpty()) {
                invalidHatSelected = true;
                SpireAnniversary5Mod.logger.info("Selected Random but no hats are unlocked.");
                Hats.removeHat(false);
                flavorText = TEXT[8];
                showRainbowButton = false;
                if (rainbowButton.toggle.enabled) rainbowButton.toggle.toggle();
            } else {
                invalidHatSelected = false;
                //SpireAnniversary5Mod.logger.info("Selected Random.");
                Hats.addHat(false, "Locked");
                randomHatMode = true;
                flavorText = TEXT[8];
                if (!currentlyUnlockedRainbows.isEmpty()) {
                    showRainbowButton = true;
                } else {
                    showRainbowButton = false;
                    if (rainbowButton.toggle.enabled) rainbowButton.toggle.toggle();
                }
            }
        } else if (!currentlyUnlockedHats.contains(hats.get(index))) {
            //SpireAnniversary5Mod.logger.info("Selected a locked hat.");
            invalidHatSelected = true;
            currentHat = null;
            Hats.addHat(false, "Locked");
            flavorText = TEXT[2] + SpireAnniversary5Mod.packsByID.get(hats.get(index)).name + TEXT[3];
            showRainbowButton = false;
            if (rainbowButton.toggle.enabled) rainbowButton.toggle.toggle();
        } else if (name.contains(TEXT[6])) {
            invalidHatSelected = true;
            currentHat = null;
            //SpireAnniversary5Mod.logger.info("Selected a missing hat.");
            Hats.removeHat(false);
            flavorText = SpireAnniversary5Mod.packsByID.get(hats.get(index)).name + TEXT[7];
            showRainbowButton = false;
            if (rainbowButton.toggle.enabled) rainbowButton.toggle.toggle();
        } else {
            invalidHatSelected = false;
            //SpireAnniversary5Mod.logger.info("Add new hat at index " + index);
            currentHat = hats.get(index);
            Hats.addHat(false, currentHat);
            flavorText = SpireAnniversary5Mod.packsByID.get(hats.get(index)).name + TEXT[10] + '\n' +
                    SpireAnniversary5Mod.packsByID.get(currentHat).getHatFlavor();
            doRainbowButtonLogic(currentHat);
        }
        try {
            SpireAnniversary5Mod.saveLastPickedHatID(hats.get(index));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void doRainbowButtonLogic(String hat) {
        if (currentlyUnlockedRainbows.contains(hat)) {
            showRainbowButton = true;
            if (rainbowButton.toggle.enabled) SpireAnniversary5Mod.isHatRainbow = true;
        } else {
            showRainbowButton = false;
            if (rainbowButton.toggle.enabled) {
                rainbowButton.toggle.toggle();
            }
        }
    }

    private static Color getColorFromIndex(int index) {
        if (currentlyUnseenHats.contains(hats.get(index))) {
            return UNSEEN_COLOR;
        } else if (index == 0) {
            if (currentlyUnlockedRainbows.contains(CoreSetPack.ID)) {
                return RAINBOW_UNLOCKED_COLOR;
            } else {
                return null;
            }
        } else if (index == 1) {
            return currentlyUnlockedRainbows.size() == hats.size() ? RAINBOW_UNLOCKED_COLOR : null;
        } else if (currentlyUnlockedRainbows.contains(hats.get(index))) {
            return RAINBOW_UNLOCKED_COLOR;
        } else if (currentlyUnlockedHats.contains(hats.get(index))) {
            return null;
        } else {
            return LOCKED_COLOR;
        }
    }

    private static String flavorText = "";

    public void update() {
        dropdown.update();
        if (showRainbowButton && !dropdown.isOpen) rainbowButton.update();
        FontHelper.cardTitleFont.getData().setScale(1f);
    }

    public void render(SpriteBatch sb) {
        sb.draw(MENU_BG, BG_X, BG_Y, 0f, 0f, MENU_BG.getRegionWidth(), MENU_BG.getRegionHeight(), BG_X_SCALE, BG_Y_SCALE, 0f);

        FontHelper.renderWrappedText(sb, FontHelper.panelNameFont, flavorText, FLAVOR_X, DROPDOWN_Y - (343 * Settings.yScale), 330 * Settings.xScale, Color.YELLOW.cpy(), 0.8F);

        getDummy().renderPlayerImage(sb);

        if (showRainbowButton) rainbowButton.render(sb);

        dropdown.render(sb, DROPDOWN_X, DROPDOWN_Y);


    }

}
