package thePackmaster.skins.instances;

import thePackmaster.skins.AbstractSkin;

public class PackmistressSkin extends AbstractSkin {
    public static String SKINID = "packmistress";

    private static PackmistressSkin instance;

    private PackmistressSkin() {
        id = SKINID;
        texturePath = SKINID;
    }

    public static AbstractSkin getInstance() {
        if(instance == null) {
            instance = new PackmistressSkin();
        }
        return instance;
    }
}

