package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.hats.HatMenu;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.ui.FixedModLabeledToggleButton.FixedModLabeledButton;
import thePackmaster.ui.PackFilterMenu;

import java.io.IOException;
import java.util.*;

import static thePackmaster.SpireAnniversary5Mod.PACKS_PER_CHOICE;
import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MainMenuUIPatch {
    public static boolean customDraft;

    private static final Hitbox packDraftToggle = new Hitbox(40.0f * Settings.scale, 40.0f * Settings.scale);
    private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("PackMainMenuUI"));
    private static final String[] TEXT = uiStrings.TEXT;

    private static final ArrayList<PowerTip> toggleTips = new ArrayList<>();

    private static final ArrayList<DropdownMenu> dropdowns = new ArrayList<>();
    public static final ArrayList<String> packSetups = new ArrayList<>();

    private static final ArrayList<String> options = new ArrayList<>();
    private static final String[] optionIDs;
    public static final String RANDOM = "Random";
    public static final String CHOICE = "Choice";
    public static final String NONE = "None";

    private static final float CHECKBOX_X_OFF = 32.0f * Settings.xScale;
    private static final float CHECKBOX_X;
    private static final float CHECKBOX_Y = Settings.HEIGHT / 2.0f - 175.0f * Settings.yScale;

    private static final int PACK_COUNT = SpireAnniversary5Mod.PACKS_PER_RUN;
    private static final int DROPDOWN_ROWCOUNT = 10;
    private static final float DROPDOWNS_SPACING = 50F * Settings.scale;
    private static final float DROPDOWN_X;
    private static final float DROPDOWNS_START_Y = CHECKBOX_Y + DROPDOWNS_SPACING * (PACK_COUNT + 0.5f);

    //filter button fields
    // We pass these values to the button class, which does its own multiplication by xScale/yScale
    private static final float FILTERBUTTON_X = 55f;
    private static final float FILTERBUTTON_Y = 1080f - 122f;

    private static final PackFilterMenu filterMenu = new PackFilterMenu();
    private static final FixedModLabeledButton openFilterMenuButton;
    private static final HashMap<String, Integer> idToIndex = new HashMap<>();

    //hat button fields
    // We pass these values to the button class, which does its own multiplication by xScale/yScale
    private static final float HATBUTTON_X = 610f;
    private static final float HATBUTTON_Y = 1080f - 122f;

    public static final HatMenu hatMenu = new HatMenu();
    private static final FixedModLabeledButton openHatMenuButton;

    static {
        options.add(TEXT[2]);
        options.add(TEXT[3] + PACKS_PER_CHOICE + TEXT[6]);
        options.add(TEXT[7]);
        List<AbstractCardPack> sortedPacks = new ArrayList<>(SpireAnniversary5Mod.unfilteredAllPacks);
        sortedPacks.sort(Comparator.comparing((pack) -> pack.name));
        for (AbstractCardPack c : sortedPacks) {
            options.add(c.name);
        }

        optionIDs = new String[options.size()];
        optionIDs[0] = RANDOM;
        optionIDs[1] = CHOICE;
        optionIDs[2] = NONE;
        idToIndex.put(RANDOM, 0);
        idToIndex.put(CHOICE, 1);
        idToIndex.put(NONE, 2);
        int autoOptions = idToIndex.size();
        //I have some jank code in the dropdowns below that excludes the last option here which is NONE so keep that in mind in case you add more.
        for (int i = autoOptions; i < optionIDs.length; ++i) {
            String packID = sortedPacks.get(i - autoOptions).packID;
            optionIDs[i] = packID;
            idToIndex.put(packID, i);
        }

        customDraft = SpireAnniversary5Mod.getCustomDraftEnabled();

        //Validate the saved CDraftSelection - this is necessary in the event that any packs are removed.
        //Without this validation, Packmaster will crash on attempting to load a Pack that is no longer in the pack list.
        ArrayList<String> packSetupsInit = new ArrayList<>(SpireAnniversary5Mod.getSavedCDraftSelection());

        for (String s : packSetupsInit) {
            switch (s) {
                case RANDOM:
                case CHOICE:
                case NONE:
                    packSetups.add(s);
                    break;
                default:
                    if (SpireAnniversary5Mod.packsByID.getOrDefault(s, null) != null) {
                        packSetups.add(s);
                    } else {
                        packSetups.add(RANDOM); //This will only get hit if there is an invalid entry being loaded, such as Pack that no longer exists.  In that event, replace it with RANDOM.
                    }
            }
        }
        // As part of adding NONE as an option, a bug was introduced that didn't include anything in the pack setups for
        // the NONE option, leaving it with fewer entries than the expected amount. To fix this, we fill any missing
        // entries with NONE here, so saved pack setups loaded while this bug existed will work properly (and so that
        // downstream code doesn't need to check for this everywhere)
        for (int i = packSetups.size(); i < PACK_COUNT; i++) {
            packSetups.add(NONE);
        }
        packSetupsInit.clear();

        for (int i = 0; i < PACK_COUNT; i++) {
            int index = i;
            DropdownMenu d = new DropdownMenu((dropdownMenu, optionIndex, s) -> {
                packSetups.set(index, optionIDs[optionIndex]);
                //Jank to allow config for allowing multiple Nones to be chosen
                int excluded = autoOptions;
                if(!SpireAnniversary5Mod.allowMultiNone()) excluded--;
                if (optionIndex >= excluded) {
                    for (DropdownMenu other : dropdowns) {
                        if (other != dropdownMenu && other.getSelectedIndex() == optionIndex) {
                            other.setSelectedIndex(0);
                        }
                    }
                }
                try {
                    SpireAnniversary5Mod.saveCDraftSelection(packSetups);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }, options, FontHelper.tipBodyFont, Settings.CREAM_COLOR, DROPDOWN_ROWCOUNT);

            dropdowns.add(d);
        }
        for (int i = 0; i < dropdowns.size(); i++) {
            DropdownMenu ddm = dropdowns.get(i);
            ddm.setSelectedIndex(idToIndex.get(packSetups.get(i)));
        }

        float dropdownX = Settings.WIDTH - (50.0f * Settings.scale) - dropdowns.get(0).approximateOverallWidth();
        float checkboxX = Settings.WIDTH - 82F * Settings.scale - FontHelper.getWidth(FontHelper.tipHeaderFont, uiStrings.TEXT[0], 1);

        if (checkboxX - dropdownX >= 60) {
            DROPDOWN_X = dropdownX;
            CHECKBOX_X = checkboxX + CHECKBOX_X_OFF;
        } else {
            DROPDOWN_X = Math.min(dropdownX, checkboxX);
            CHECKBOX_X = DROPDOWN_X + CHECKBOX_X_OFF;
        }

        openFilterMenuButton = new FixedModLabeledButton(uiStrings.TEXT[4], FILTERBUTTON_X, FILTERBUTTON_Y, null,
                (button) -> filterMenu.toggle());

        openHatMenuButton = new FixedModLabeledButton(uiStrings.TEXT[5], HATBUTTON_X, HATBUTTON_Y, null, (button) -> hatMenu.toggle());
    }

    public static void updateChoiceCount() {
        options.set(1, TEXT[3] + PACKS_PER_CHOICE + TEXT[6]);

        for (DropdownMenu dropdown : dropdowns) {
            Object o = dropdown.rows.get(1);
            ReflectionHacks.setPrivate(o, o.getClass(), "text", options.get(1));
        }
    }


    @SpirePatch(clz = CharacterOption.class, method = "renderRelics")
    public static class RenderOptions {
        public static void Postfix(CharacterOption obj, SpriteBatch sb) {

            CharSelectInfo c = ReflectionHacks.getPrivate(obj, CharacterOption.class, "charInfo");

            if (c != null && c.player.chosenClass.equals(ThePackmaster.Enums.THE_PACKMASTER) && obj.selected) {
                // Render toggle button
                packDraftToggle.move(CHECKBOX_X, CHECKBOX_Y);
                packDraftToggle.render(sb);

                sb.setColor(Color.WHITE);
                float checkScale = Settings.scale * 0.8f;
                sb.draw(ImageMaster.CHECKBOX, packDraftToggle.cX - 32f, packDraftToggle.cY - 32f, 32.0f, 32.0f, 64.0f, 64.0f, checkScale, checkScale, 0.0f, 0, 0, 64, 64, false, false);
                if (customDraft) {
                    sb.draw(ImageMaster.TICK, packDraftToggle.cX - 32f, packDraftToggle.cY - 32f, 32.0f, 32.0f, 64.0f, 64.0f, checkScale, checkScale, 0.0f, 0, 0, 64, 64, false, false);
                }
                FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, uiStrings.TEXT[0], packDraftToggle.cX + 25f * Settings.scale, packDraftToggle.cY + FontHelper.getHeight(FontHelper.tipHeaderFont) * 0.5f, Settings.BLUE_TEXT_COLOR);

                // If toggle button is checked, render the dropdowns, too
                if (customDraft) {
                    for (int i = dropdowns.size() - 1; i >= 0; i--) {
                        dropdowns.get(i).render(sb, DROPDOWN_X, DROPDOWNS_START_Y - (DROPDOWNS_SPACING * i));
                    }
                }

                if (filterMenu.isOpen) {
                    filterMenu.render(sb);
                }
                openFilterMenuButton.render(sb);

                if (hatMenu.isOpen) {
                    hatMenu.render(sb);
                }
                openHatMenuButton.render(sb);
            }
        }
    }

    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
    public static class UpdateOptions {
        public static void Postfix(CharacterOption obj) {
            CharSelectInfo c = ReflectionHacks.getPrivate(obj, CharacterOption.class, "charInfo");

            if (c != null && c.player.chosenClass.equals(ThePackmaster.Enums.THE_PACKMASTER) && obj.selected) {
                // If custom draft is enabled, update the dropdowns

                boolean stopInput = false;
                if (customDraft) {
                    for (DropdownMenu d : dropdowns) {
                        if (d.isOpen)
                            stopInput = true;
                        d.update();
                        if (d.isOpen || stopInput) {
                            stopInput = true;
                            InputHelper.justClickedLeft = false;
                            InputHelper.justReleasedClickLeft = false;
                            CInputActionSet.select.unpress();
                            CInputActionSet.proceed.unpress();
                        }
                    }
                }

                // Update the toggle button.
                if (!stopInput) {
                    packDraftToggle.update();
                    if (packDraftToggle.hovered) {
                        if (toggleTips.isEmpty()) {
                            toggleTips.add(new PowerTip(uiStrings.TEXT[0], uiStrings.TEXT[1]));
                        }
                        if (InputHelper.mX < 1400.0f * Settings.scale) {
                            TipHelper.queuePowerTips(InputHelper.mX + 60.0f * Settings.scale, InputHelper.mY - 50.0f * Settings.scale, toggleTips);
                        } else {
                            TipHelper.queuePowerTips(InputHelper.mX - 350.0f * Settings.scale, InputHelper.mY - 50.0f * Settings.scale, toggleTips);
                        }

                        if (InputHelper.justClickedLeft) {
                            CardCrawlGame.sound.playA("UI_CLICK_1", -0.4f);
                            packDraftToggle.clickStarted = true;
                        }
                        if (packDraftToggle.clicked) {
                            customDraft = !customDraft;
                            SpireAnniversary5Mod.saveCustomDraftEnabled(customDraft);
                            packDraftToggle.clicked = false;
                        }
                    }
                } else {
                }

                openFilterMenuButton.update();
                if (filterMenu.isOpen) {
                    filterMenu.update();
                }

                openHatMenuButton.update();
                if (hatMenu.isOpen) {
                    hatMenu.update();
                }
            }
        }
    }
}
