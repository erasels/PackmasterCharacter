package thePackmaster.skins;

import com.badlogic.gdx.Gdx;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.ThePackmaster;
import thePackmaster.skins.instances.PackmasterSkin;

import static thePackmaster.SpireAnniversary5Mod.modID;

public abstract class AbstractSkin {
    public static final String SKINS_DIR = modID + "Resources/images/char/mainChar/skins/";

    public String id, author, name, texturePath;
    protected float scale = 1f;
    protected boolean renderHats = true;

    public AbstractSkin() {
    }

    public String getShoulder1Path() {
        return getSkinPath(ThePackmaster.SHOULDER1);
    }

    public String getShoulder2Path() {
        return getSkinPath(ThePackmaster.SHOULDER2);
    }

    public String getCorpsePath() {
        return getSkinPath(ThePackmaster.CORPSE);
    }

    public String getSkeletonJSONPath() {
        return getSkinPath(ThePackmaster.SKELETON_JSON);
    }

    public String getSkeletonAtlasPath() {
        return getSkinPath(ThePackmaster.SKELETON_ATLAS);
    }

    public float getScale() {
        return scale;
    }

    protected void loadStrings() {
        UIStrings str = CardCrawlGame.languagePack.getUIString(SkinHandler.makeSkinID(id));
        name = str.TEXT[0];
        author = str.TEXT[1];
    }

    protected String getSkinPath(String input) {
        String skinPath = SKINS_DIR + texturePath + "/"+ input;
        if(!Gdx.files.internal(skinPath).exists()) {
            skinPath = SKINS_DIR + PackmasterSkin.SKINID + "/"+ input; //return base skinPath if the image doesn't exist
        }
        return skinPath;
    }
}
