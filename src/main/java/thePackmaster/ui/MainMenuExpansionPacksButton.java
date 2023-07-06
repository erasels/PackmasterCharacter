package thePackmaster.ui;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.ModList;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.ui.buttons.Button;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;
import thePackmaster.vfx.GlowParticle;
import thePackmaster.vfx.VictoryConfettiEffect;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainMenuExpansionPacksButton extends Button {
    private static String[] TEXT;
    public static final Texture tex = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/MMReminderBtn.png"));
    public static boolean singleTimeSetup = true;
    private static final float PARTICLE_CD = 0.7f;
    private float particleTimer = PARTICLE_CD;
    private ArrayList<AbstractGameEffect> effects = new ArrayList<>();
    private ArrayList<AbstractGameEffect> glows = new ArrayList<>();

    private static final float TRANSPARENCY_LIMIT = 0.4f;
    private boolean fadingOut = false;
    private float transparency = 1f;

    public MainMenuExpansionPacksButton(float x, float y) {
        super(x, y, tex);
        hb = new Hitbox(x, y, tex.getWidth() * Settings.xScale, tex.getHeight() * Settings.yScale);
    }

    @Override
    public void update() {
        if (CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.MAIN_MENU)
            return;

        fadeOutUpdate();

        super.update();

        updateGlow();
        updateParticles();

        if(pressed) {
            if(singleTimeSetup) {
                fadingOut = true;
                ModInfo[] allMods = ReflectionHacks.getPrivateStatic(Loader.class, "ALLMODINFOS");
                boolean found = false;
                for (ModInfo m : allMods) {
                    if (m.ID.equals(SpireAnniversary5Mod.expansionPackModID)) {
                        found = true;
                        ArrayList<File> newModList = new ArrayList<>();
                        for (ModInfo loadedMI : Loader.MODINFOS) {
                            try {
                                newModList.add(new File(loadedMI.jarURL.toURI()));
                                //Add expansion right after main mod
                                if (loadedMI.ID.equals(SpireAnniversary5Mod.modID)) {
                                    newModList.add(new File(m.jarURL.toURI()));
                                }
                            } catch (URISyntaxException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        ModList.save(ModList.getDefaultList(), newModList.toArray(new File[0]));
                        break;
                    }
                }
                if (!found) {
                    SpireAnniversary5Mod.logger.error("Could not find expansion packs??? Please open a bug report on the steam workshop.");
                }
                singleTimeSetup = false;
            }

            return;
        }

        if(hb.hovered) {
            TipHelper.renderGenericTip((float) InputHelper.mX - 350.0F * Settings.xScale,
                    (float) InputHelper.mY + 50.0F * Settings.yScale,
                    TEXT[0],
                    TEXT[1]
            );
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        glows.forEach(g -> g.render(sb));

        if (this.hb.hovered || pressed) {
            inactiveColor.a = transparency;
            sb.setColor(inactiveColor);
        } else {
            sb.setColor(activeColor);
        }

        sb.draw(
                tex,
                x,
                y,
                tex.getWidth() * Settings.scale,
                tex.getHeight() * Settings.scale
        );

        sb.setColor(Color.WHITE);

        if(pressed) {
            FontHelper.renderFontRightAligned(sb, FontHelper.buttonLabelFont, TEXT[2], hb.x, hb.cY, Settings.RED_TEXT_COLOR);
            return;
        }
        hb.render(sb);

        effects.forEach(e -> e.render(sb));
    }

    private static final float GLOW_TIME = 0.75f;
    private float glowtimer = 0;

    protected void updateGlow() {
        glows.forEach(AbstractGameEffect::update);
        glows.removeIf(g -> g.isDone);

        if(pressed) {
            return;
        }

        glowtimer -= Gdx.graphics.getDeltaTime();
        if(glowtimer <= 0) {
            glowtimer = GLOW_TIME;
            glows.add(new GlowParticle(tex, hb.cX, hb.cY, 0, 1.5f));
        }
    }

    protected void updateParticles() {
        effects.forEach(AbstractGameEffect::update);
        effects.removeIf(e -> e.isDone);

        if(pressed) {
            return;
        }

        this.particleTimer -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer < 0.0F) {
            this.particleTimer = PARTICLE_CD;
            float expandMod = 5f * Settings.xScale;

            for (int i = 0; i < MathUtils.random(1, 3); i++) {
                float starX = hb.x - expandMod, endX = starX + hb.width + expandMod;
                float startY = hb.y, endY = startY + hb.height;

                effects.add(new VictoryConfettiEffect(MathUtils.random(starX, endX), MathUtils.random(startY, endY), MathUtils.random(0.2f, 0.5f)));
            }
        }
    }

    protected void fadeOutUpdate() {
        if(fadingOut && transparency > TRANSPARENCY_LIMIT) {
            transparency -= Gdx.graphics.getDeltaTime() / 2f;
        }
    }

    public static void initStrings() {
        TEXT = CardCrawlGame.languagePack.getUIString(SpireAnniversary5Mod.makeID("MainMenuExpansionPacksButton")).TEXT;
    }
}
