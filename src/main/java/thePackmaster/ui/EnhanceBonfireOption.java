package thePackmaster.ui;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.packs.GemsPack;
import thePackmaster.util.TexLoader;
import thePackmaster.vfx.gemspack.SocketGemEffect;


public class EnhanceBonfireOption extends AbstractCampfireOption {
    public static final String[] DESCRIPTIONS;
    private static final UIStrings UI_STRINGS;

    static {
        UI_STRINGS = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("EnhanceBonfireOptions"));
        DESCRIPTIONS = UI_STRINGS.TEXT;

    }

    //private ArrayList<String> idleMessages;
    public EnhanceBonfireOption(boolean active) {
        this.label = DESCRIPTIONS[0];

        this.usable = active;
        if (active) {
            this.description = DESCRIPTIONS[1] + GemsPack.goldCostToSocket + DESCRIPTIONS[2];
            this.img = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/scrapcampfire.png"));
        } else {
            this.img = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/scrapcampfiredisabled.png"));
            if (AbstractDungeon.player.gold < GemsPack.goldCostToSocket) {
                this.description = DESCRIPTIONS[3] + GemsPack.goldCostToSocket + DESCRIPTIONS[4];
            } else {
                this.description = DESCRIPTIONS[3] + GemsPack.goldCostToSocket + DESCRIPTIONS[4];
            }
        }
    }

    @Override
    public void useOption() {
        if (this.usable) {
            GemsPack.currSocketGemEffect = new SocketGemEffect();
            AbstractDungeon.effectList.add(GemsPack.currSocketGemEffect);

        }
    }

    public void reCheck() {
        if (SocketGemEffect.getModifiableCards().isEmpty() || SocketGemEffect.getGems().isEmpty() || AbstractDungeon.player.gold < GemsPack.goldCostToSocket) {
            this.usable = false;
        }
        if (this.usable) {
            this.description = DESCRIPTIONS[1] + GemsPack.goldCostToSocket + DESCRIPTIONS[2];
            this.img = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/scrapcampfire.png"));
        } else {
            this.description = DESCRIPTIONS[3] + GemsPack.goldCostToSocket + DESCRIPTIONS[4];
            this.img = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/scrapcampfiredisabled.png"));
        }
    }

    @Override
    public void update() {
        float hackScale = ReflectionHacks.getPrivate(this, AbstractCampfireOption.class, "scale");
        //This allows the button to be mouse over even when you don't meet the requirements, so you can see the tooltip.
        // The scale change mimics what normally happens when the button is active.
        // Normally greyed out options lose their hitbox, and we wanted to have this one be able to tell the player why its greyed out.
        if (this.hb.hovered) {

            if (!this.hb.clickStarted) {
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));

            } else {
                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));

            }
        } else {
            ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));
        }
        super.update();
    }
}
