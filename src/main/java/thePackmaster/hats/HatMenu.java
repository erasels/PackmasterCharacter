package thePackmaster.hats;

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
import thePackmaster.hats.specialhats.SpecialHat;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.AlignmentPack;
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
    public static final Map<String, SpecialHat> specialHats = new HashMap<>();

    static {
        specialHats.put(AlignmentPack.ID, new AlignmentHat());
    }

    public static final String[] TEXT = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("HatMenu")).TEXT;
    private static final TextureRegion MENU_BG = new TextureRegion(ImageMaster.loadImage("img/ModPanelBg.png"));

    //positions
    private static final float BG_X_SCALE = Settings.scale * 0.275f;
    private static final float BG_Y_SCALE = Settings.scale * 0.8f;
    private static final float BG_X = 525f * Settings.xScale;
    private static final float BG_Y = Settings.HEIGHT - 40f * Settings.yScale - MENU_BG.getRegionHeight() * BG_Y_SCALE;
    private static final float DROPDOWN_X = 550f * Settings.xScale;
    private static final float DROPDOWN_Y = Settings.HEIGHT - 160f * Settings.yScale;
    private static final float PREVIEW_X = BG_X + (210 * Settings.scale);
    private static final float PREVIEW_Y = BG_Y + (225 * Settings.scale);

    public static AbstractPlayer dummy;

    private static final boolean UNLOCK_ALL_HATS = false;

    public static int currentHatIndex;


    public HatMenu() {
        refreshHatDropdown();

        dummy = new ThePackmaster("", ThePackmaster.Enums.THE_PACKMASTER);
        dummy.drawX = PREVIEW_X;
        dummy.drawY = PREVIEW_Y;

        dummy.animX = dummy.animY = 0;
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


        if (init) {
            int lastPickedIdx = SpireAnniversary5Mod.getLastPickedHatIndex();
            setCurrentHat(lastPickedIdx, optionNames.get(lastPickedIdx));
        }
    }

    public static ArrayList<String> getHatDropdownStrings() {
        ArrayList<String> unlockedHats = SpireAnniversary5Mod.getUnlockedHats();

        ArrayList<String> optionNames = new ArrayList<>();
        optionNames.add(TEXT[0]);
        optionNames.add(TEXT[9]);
        ArrayList<AbstractCardPack> sortedPacks = new ArrayList<>(SpireAnniversary5Mod.unfilteredAllPacks);
        sortedPacks.sort(Comparator.comparing((pack) -> pack.name));
        for (AbstractCardPack s : sortedPacks) {
            if (unlockedHats.contains(s.packID)) SpireAnniversary5Mod.logger.info("Hat unlock exists: " + s.packID);
            if (UNLOCK_ALL_HATS)
                SpireAnniversary5Mod.logger.info("Unlock All Hats enabled and is unlocking " + s.packID);

            if (Gdx.files.internal(Hats.getImagePathFromHatID(s.packID)).exists() || specialHats.containsKey(s.packID)) {
                if (UNLOCK_ALL_HATS || unlockedHats.contains(s.packID)) {
                    hats.add(s.packID);
                    if (unlockedHats.contains(s.packID)) {
                        currentlyUnlockedHats.add(s.packID);
                    }
                    optionNames.add(s.getHatName());
                } else {
                    hats.add(s.packID);
                    optionNames.add(TEXT[1] + " " + s.getHatName());
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
        currentHat = Wiz.getRandomItem(currentlyUnlockedHats);
        Hats.addHat(true, currentHat);
        SpireAnniversary5Mod.logger.info("Randomizer chose hat: " + currentHat);
    }

    public static void setCurrentHat(int index, String name) {
        currentHatIndex = index;
        randomHatMode = false;
        if (index == 0) {
            invalidHatSelected = false;
            SpireAnniversary5Mod.logger.info("Removing hat.");
            Hats.removeHat(false);
            flavorText = "";
        } else if (index == 1) {
            if (currentlyUnlockedHats.isEmpty()) {
                invalidHatSelected = true;
                SpireAnniversary5Mod.logger.info("Selected Random but no hats are unlocked.");
                Hats.removeHat(false);
                flavorText = TEXT[8];
            } else {
                invalidHatSelected = false;
                SpireAnniversary5Mod.logger.info("Selected Random.");
                Hats.addHat(false, "Locked");
                randomHatMode = true;
                flavorText = TEXT[8];
            }
        } else if (name.contains(TEXT[1])) {
            SpireAnniversary5Mod.logger.info("Selected a locked hat.");
            invalidHatSelected = true;
            Hats.addHat(false, "Locked");
            flavorText = TEXT[2] + SpireAnniversary5Mod.packsByID.get(hats.get(index - 2)).name + TEXT[3];
        } else if (name.contains(TEXT[6])) {
            invalidHatSelected = true;
            SpireAnniversary5Mod.logger.info("Selected a missing hat.");
            Hats.removeHat(false);
            flavorText = SpireAnniversary5Mod.packsByID.get(hats.get(index - 2)).name + TEXT[7];
        } else {
            invalidHatSelected = false;
            SpireAnniversary5Mod.logger.info("Add new hat at index " + index);
            currentHat = hats.get(index - 2);
            Hats.addHat(false, currentHat);
            flavorText = SpireAnniversary5Mod.packsByID.get(currentHat).getHatFlavor();
        }
        try {
            SpireAnniversary5Mod.saveLastPickedHatIndex(index);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String flavorText = "";

    public void update() {
        dropdown.update();
        FontHelper.cardTitleFont.getData().setScale(1f);
    }

    public void render(SpriteBatch sb) {
        sb.draw(MENU_BG, BG_X, BG_Y, 0f, 0f, MENU_BG.getRegionWidth(), MENU_BG.getRegionHeight(), BG_X_SCALE, BG_Y_SCALE, 0f);

        FontHelper.renderWrappedText(sb, FontHelper.panelNameFont, flavorText, DROPDOWN_X + (175 * Settings.scale), DROPDOWN_Y - (333 * Settings.scale), 300 * Settings.scale, Color.YELLOW.cpy(), 0.8F);

        dummy.renderPlayerImage(sb);

        dropdown.render(sb, DROPDOWN_X, DROPDOWN_Y);


    }

}
