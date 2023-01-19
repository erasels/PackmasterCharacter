package thePackmaster.ui;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import thePackmaster.util.TexLoader;


public class InfestTextIcon extends AbstractCustomIcon {
    public static final String ID = "bug";
    private static InfestTextIcon singleton;
    private static final Texture iconTex = TexLoader.getTexture("anniv5Resources/images/ui/CardTextInfestIcon.png");

    public InfestTextIcon() {
        super(ID, iconTex);
    }

    public static InfestTextIcon get() {
        if (singleton == null) {
            singleton = new InfestTextIcon();
        }
        return singleton;
    }
}
