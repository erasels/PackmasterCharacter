package thePackmaster.skins.instances;

import thePackmaster.skins.AbstractSkin;

public class PackmasterSkin extends AbstractSkin {
    public static String SKINID = "packmaster";

    private static PackmasterSkin instance;

    private PackmasterSkin() {
        id = SKINID;
        texturePath = SKINID;
    }

    public static AbstractSkin getInstance() {
        if(instance == null) {
            instance = new PackmasterSkin();
        }
        return instance;
    }
}
