package thePackmaster.skins;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.skins.instances.PackmasterSkin;

import java.util.HashMap;

public class SkinHandler {
    private static SkinHandler instance;
    public static final String CONFIG_CURRENT_SKIN = "PackmasterCurrentSkinID";
    public static HashMap<String, AbstractSkin> skinMap;

    private AbstractSkin currentSkin;

    private SkinHandler() {
        //Populate skinMap
        skinMap = new HashMap<>();
        registerSkin(PackmasterSkin.getInstance());

        //No data is being loaded yet
        currentSkin = skinMap.get(SpireAnniversary5Mod.modConfig.getString(CONFIG_CURRENT_SKIN));
    }

    public void initializeStrings() {
        for(AbstractSkin skin : skinMap.values()) {
            skin.loadStrings();
        }
    }

    public AbstractSkin curSkin() {
        return currentSkin;
    }

    public void loadSkin(String skinID, AbstractPlayer player) {
        currentSkin = skinMap.get(skinID);
        if(player instanceof ThePackmaster) {
            ((ThePackmaster) player).loadSkinData(currentSkin);
        }
    }

    public void loadSkin(String skinID) {
        loadSkin(skinID, AbstractDungeon.player);
    }

    public void loadCurrentSkin(AbstractPlayer player) {
        loadSkin(currentSkin.id, player);
    }

    public void saveCurSkin() {
        try {
            SpireAnniversary5Mod.modConfig.setString(CONFIG_CURRENT_SKIN, currentSkin.id);
            SpireAnniversary5Mod.modConfig.save();
        } catch (Exception e) {
            SpireAnniversary5Mod.logger.error(e);
        }
    }

    public static String makeSkinID(String id) {
        return SpireAnniversary5Mod.makeID(id + "Skin");
    }

    public static SkinHandler getInstance() {
        if(instance == null) {
            instance = new SkinHandler();
        }
        return instance;
    }

    private void registerSkin(AbstractSkin skin) {
        skinMap.put(skin.id, skin);
    }
}
