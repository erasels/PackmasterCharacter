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

import java.util.ArrayList;

import static thePackmaster.SpireAnniversary5Mod.makeID;

public class MainMenuUIPatch {
    public static boolean customDraft = false;
    public static final Hitbox packDraftToggle = new Hitbox(40.0f * Settings.scale * (0.01f + (1.0f - 0.019f)), 40.0f * Settings.scale);
    public static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(makeID("PackMainMenuUI"));
    public static final ArrayList<PowerTip> toggleTips = new ArrayList<>();

    public static final ArrayList<DropdownMenu> dropdowns = new ArrayList<>();
    public static final ArrayList<String> packSetups = new ArrayList<>();
    public static final ArrayList<String> options = new ArrayList<>();
    public static final ArrayList<ArrayList<String>> optionsPerDropdown = new ArrayList<>();

    public static final float CHECKBOX_X = Settings.WIDTH - 200F * Settings.scale;
    public static final float CHECKBOX_Y = Settings.HEIGHT / 2.0f - 175.0f * Settings.scale;

    public static final float DROPDOWNS_START_X = Settings.WIDTH - 800F * Settings.scale;
    public static final float DROPDOWNS_START_Y = Settings.HEIGHT / 2.0F + 175F * Settings.scale;
    public static final float DROPDOWNS_SPACING = 50F * Settings.scale;

    static { // Todo: find a way to use enums for this maybe, while still allowing for specific names.
        options.add("Random");
        options.add("Choice of 3");
        for (AbstractCardPack c : SpireAnniversary5Mod.allPacks) {
            options.add(c.name);
        }

        for (int i = 0; i < 7; i++) {
            ArrayList<String> specificOptions = new ArrayList<>();
            specificOptions.addAll(options);
            optionsPerDropdown.add(specificOptions);
        }

        packSetups.add("Core Set");
        packSetups.add("Random");
        packSetups.add("Random");
        packSetups.add("Choice of 3");
        packSetups.add("Choice of 3");
        packSetups.add("Choice of 3");
        packSetups.add("Choice of 3");

        for (int i = 0; i < 7; i++) {
            DropdownMenu d = new DropdownMenu((dropdownMenu, q, s) -> {
                packSetups.set(dropdowns.indexOf(dropdownMenu), s);
                for (DropdownMenu other : dropdowns) {
                    if (!s.equals("Random") && !s.equals("Choice of 3") && other != dropdownMenu && other.getSelectedIndex() == q) {
                        other.setSelectedIndex(0);
                    }
                }
            }, optionsPerDropdown.get(i), FontHelper.tipBodyFont, Settings.CREAM_COLOR);

            dropdowns.add(d);

            if (i == 0) {
                d.setSelectedIndex(2);
            } else if (i >= 2) {
                d.setSelectedIndex(1);
            }
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
                sb.draw(ImageMaster.CHECKBOX, packDraftToggle.cX - 32.0f, packDraftToggle.cY - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale * (0.01f + (1.0f - 0.019f)), Settings.scale * (0.01f + (1.0f - 0.019f)), 0.0f, 0, 0, 64, 64, false, false);
                if (customDraft) {
                    sb.draw(ImageMaster.TICK, packDraftToggle.cX - 32.0f, packDraftToggle.cY - 32.0f, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale * (0.01f + (1.0f - 0.019f)), Settings.scale * (0.01f + (1.0f - 0.019f)), 0.0f, 0, 0, 64, 64, false, false);
                }
                FontHelper.renderSmartText(sb, FontHelper.tipHeaderFont, uiStrings.TEXT[0], packDraftToggle.cX + 25f * Settings.scale, packDraftToggle.cY, Settings.BLUE_TEXT_COLOR);

                // If toggle button is checked, render the dropdowns, too

                if (customDraft) {
                    for (int i = dropdowns.size() - 1; i >= 0; i--) {
                        dropdowns.get(i).render(sb, DROPDOWNS_START_X, DROPDOWNS_START_Y - (DROPDOWNS_SPACING * i)); //TODO: Place correctly
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
