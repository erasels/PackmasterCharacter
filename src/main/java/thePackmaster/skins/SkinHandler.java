package thePackmaster.skins;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import org.apache.commons.lang3.math.NumberUtils;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.skins.instances.PackmasterSkin;
import thePackmaster.skins.instances.PackmistressSkin;
import thePackmaster.skins.instances.RandomSkin;
import thePackmaster.ui.SkinSelectionUI;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SkinHandler {
    private static SkinHandler instance;
    public static final String CONFIG_CURRENT_SKIN = "PackmasterCurrentSkinID";
    public static String randomId;
    public static LinkedHashMap<String, AbstractSkin> skinMap;

    private AbstractSkin currentSkin;

    private SkinHandler() {
        //Populate skinMap
        skinMap = new LinkedHashMap<>();
        registerSkin(RandomSkin.getInstance());
        registerSkin(PackmasterSkin.getInstance());
        registerSkin(PackmistressSkin.getInstance());

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

    public void iterateSkin(AbstractPlayer player, boolean forward) {
        ArrayList<AbstractSkin> skins = new ArrayList<>(skinMap.values());

        int curIndex = skins.indexOf(currentSkin);
        if(forward) {
            if(++curIndex >= skins.size()) curIndex = 0;
        } else {
            if(--curIndex < 0) curIndex = skins.size()-1;
        }
        currentSkin = skins.get(curIndex);

        saveCurSkin();
        loadCurrentSkin(player);
    }

    public void setUpRandom(boolean loadingSave) {
        if(!currentSkin.id.equals(RandomSkin.SKINID)) return; //Will be the correct skin when loading during the same run so this will be skipped.

        if(loadingSave) {
            loadSkin(randomId);
        } else {
            ArrayList<AbstractSkin> skins = new ArrayList<>(skinMap.values());
            AbstractSkin randSkin = skins.get(MathUtils.random(skinMap.size()-2) + 1);
            currentSkin = randSkin;
            randomId = randSkin.id;
            loadSkin(randSkin.id);
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

    public float getMaxNameLength() {
        float max = -1;
        for(AbstractSkin skin : skinMap.values()) {
            max = NumberUtils.max(FontHelper.getWidth(SkinSelectionUI.skinNameFont, skin.name, 1f), max);
        }

        return max;
    }
}
