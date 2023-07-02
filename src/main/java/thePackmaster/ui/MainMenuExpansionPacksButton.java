package thePackmaster.ui;

import basemod.ReflectionHacks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.ModList;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.ui.buttons.Button;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.util.TexLoader;
import thePackmaster.vfx.VictoryConfettiEffect;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class MainMenuExpansionPacksButton extends Button {
    public static final Texture tex = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/MMReminderBtn.png"));
    private static final float PARTICLE_CD = 0.65f;
    private float particleTimer = PARTICLE_CD;
    private ArrayList<AbstractGameEffect> effects = new ArrayList<>();

    public MainMenuExpansionPacksButton(float x, float y) {
        super(x, y, tex);
    }

    @Override
    public void update() {
        if (CardCrawlGame.mainMenuScreen.screen != MainMenuScreen.CurScreen.MAIN_MENU)
            return;

        super.update();

        if(pressed)
            return;

        this.particleTimer -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer < 0.0F) {
            this.particleTimer = PARTICLE_CD;
            float expandMod = 20f * Settings.xScale;
            float starX = hb.x - expandMod, endX = starX + hb.width + expandMod;
            float startY = hb.y - expandMod, endY = startY + hb.height + expandMod;

            effects.add(new VictoryConfettiEffect(MathUtils.random(starX, endX), MathUtils.random(startY, endY), MathUtils.random(0.22f, 0.5f)));
        }

        effects.forEach(AbstractGameEffect::update);

        if(hb.hovered) {
            TipHelper.renderGenericTip((x - tex.getWidth()) - (20f * Settings.xScale), y, "NOW!!!?!", "Enable expansion packs, fucko!");
        }

        if(pressed) {
            ModInfo[] allMods = ReflectionHacks.getPrivateStatic(Loader.class, "ALLMODINFOS");
            boolean found = false;
            for(ModInfo m : allMods) {
                if(m.ID.equals(SpireAnniversary5Mod.expansionPackModID)) {
                    found = true;
                    ArrayList<File> newModList = new ArrayList<>();
                    for(ModInfo loadedMI : Loader.MODINFOS) {
                        try {
                            newModList.add(new File(loadedMI.jarURL.toURI()));
                            //Add expansion right after main mod
                            if(loadedMI.ID.equals(SpireAnniversary5Mod.modID)) {
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
            if(!found) {
                SpireAnniversary5Mod.logger.error("Could not find expansion packs??? Please open a bug report on the steam workshop.");
            } else {
                //show that it's enabled remind them to restart and remove this button
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        effects.forEach(e -> e.render(sb));
    }
}
