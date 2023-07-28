package thePackmaster.skins.instances;

import thePackmaster.skins.AbstractSkin;

public class RandomSkin extends AbstractSkin {
    public static String SKINID = "random";

    private static RandomSkin instance;

    private RandomSkin() {
        id = SKINID;
        texturePath = SKINID;
    }

    public static AbstractSkin getInstance() {
        if(instance == null) {
            instance = new RandomSkin();
        }
        return instance;
    }
}
