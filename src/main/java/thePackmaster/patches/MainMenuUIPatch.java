package thePackmaster.patches;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.options.DropdownMenu;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.packs.CoreSetPack;

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MainMenuUIPatch {
    public static boolean customDraft = false;

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

    private static final float CHECKBOX_X_OFF = 32.0f * Settings.scale;
    private static final float CHECKBOX_X;
    private static final float CHECKBOX_Y = Settings.HEIGHT / 2.0f - 175.0f * Settings.scale;

    private static final int PACK_COUNT = 7;
    private static final float DROPDOWNS_SPACING = 50F * Settings.scale;
    private static final float DROPDOWN_X;
    private static final float DROPDOWNS_START_Y = CHECKBOX_Y + DROPDOWNS_SPACING * (PACK_COUNT + 0.5f);

    static {
        options.add(TEXT[2]);
        options.add(TEXT[3]);
        for (AbstractCardPack c : SpireAnniversary5Mod.allPacks) {
            options.add(c.name);
        }

        int coreSetPackIndex = 0;
        optionIDs = new String[options.size()];
        optionIDs[0] = RANDOM;
        optionIDs[1] = CHOICE;
        for (int i = 2; i < optionIDs.length; ++i) {
            String packID = SpireAnniversary5Mod.allPacks.get(i - 2).packID;
            optionIDs[i] = packID;
            if (packID.equals(CoreSetPack.ID)) {
                coreSetPackIndex = i;
            }
        }

        packSetups.add(CoreSetPack.ID);
        packSetups.add(optionIDs[0]);
        packSetups.add(optionIDs[0]);
        packSetups.add(optionIDs[0]);
        packSetups.add(optionIDs[0]);
        packSetups.add(optionIDs[1]);
        packSetups.add(optionIDs[1]);

        for (int i = 0; i < PACK_COUNT; i++) {
            int index = i;
            DropdownMenu d = new DropdownMenu((dropdownMenu, optionIndex, s) -> {
                packSetups.set(index, optionIDs[optionIndex]);
                if (optionIndex >= 2) {
                    for (DropdownMenu other : dropdowns) {
                        if (other != dropdownMenu && other.getSelectedIndex() == optionIndex) {
                            other.setSelectedIndex(0);
                        }
                    }
                }
            }, options, FontHelper.tipBodyFont, Settings.CREAM_COLOR);

            dropdowns.add(d);

            if (i == 0) {
                d.setSelectedIndex(coreSetPackIndex);
            } else if (i >= 5) {
                d.setSelectedIndex(1);
            }
        }

        float dropdownX = Settings.WIDTH - (50.0f * Settings.scale) - dropdowns.get(0).approximateOverallWidth();
        float checkboxX = Settings.WIDTH - 82F * Settings.scale - FontHelper.getWidth(FontHelper.tipHeaderFont, uiStrings.TEXT[0], 1);

        if (checkboxX - dropdownX >= 60) {
            DROPDOWN_X = dropdownX;
            CHECKBOX_X = checkboxX + CHECKBOX_X_OFF;
        }
        else {
            DROPDOWN_X = Math.min(dropdownX, checkboxX);
            CHECKBOX_X = DROPDOWN_X + CHECKBOX_X_OFF;
        }
    }


    @SpirePatch(clz = CharacterOption.class, method = "renderRelics")
    public static class RenderOptions {
        public static void Postfix(CharacterOption obj, SpriteBatch sb) {

            CharSelectInfo c = ReflectionHacks.getPrivate(obj, CharacterOption.class, "charInfo");

            if (c.player.chosenClass.equals(ThePackmaster.Enums.THE_PACKMASTER) && obj.selected) {
                // Render toggle button
                packDraftToggle.move(CHECKBOX_X, CHECKBOX_Y);
                packDraftToggle.render(sb);

                sb.setColor(Color.WHITE);
                float checkScale = Settings.scale * 0.8f;
                sb.draw(ImageMaster.CHECKBOX, packDraftToggle.cX - 32f, packDraftToggle.cY - 32f, 32.0f, 32.0f, 64.0f, 64.0f, checkScale, checkScale, 0.0f, 0, 0, 64, 64, false, false);
                if (customDraft) {
                    sb.draw(ImageMaster.TICK, packDraftToggle.cX - 32f, packDraftToggle.cY - 32f, 32.0f, 32.0f, 64.0f, 64.0f, checkScale, checkScale, 0.0f, 0, 0, 64, 64, false, false);
                }
                FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, uiStrings.TEXT[0], packDraftToggle.cX + 25f * Settings.scale, packDraftToggle.cY + FontHelper.getHeight(FontHelper.tipHeaderFont)*0.5f, Settings.BLUE_TEXT_COLOR);

                // If toggle button is checked, render the dropdowns, too
                if (customDraft) {
                    for (int i = dropdowns.size() - 1; i >= 0; i--) {
                        dropdowns.get(i).render(sb, DROPDOWN_X, DROPDOWNS_START_Y - (DROPDOWNS_SPACING * i)); //TODO: Place correctly
                    }
                }

            }
        }
    }

    @SpirePatch(clz = CharacterOption.class, method = "updateHitbox")
    public static class UpdateOptions {
        public static void Postfix(CharacterOption obj) {

            CharSelectInfo c = ReflectionHacks.getPrivate(obj, CharacterOption.class, "charInfo");

            if (c.player.chosenClass.equals(ThePackmaster.Enums.THE_PACKMASTER) && obj.selected) {

                // Update the toggle button.

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
                        packDraftToggle.clicked = false;
                    }
                }

                // If custom draft is enabled, update the dropdowns, too.

                if (customDraft) {
                    for (DropdownMenu d : dropdowns) {
                        d.update();
                    }
                }

            }
        }
    }
}
