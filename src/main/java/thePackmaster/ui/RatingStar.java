package thePackmaster.ui;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import thePackmaster.util.TexLoader;

public class RatingStar extends AbstractCustomIcon {
    public static final String ID = RatingStar.class.getSimpleName();
    private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingStar.class.getSimpleName() + ".png");

    public RatingStar() {
        super(ID, iconTexture);
    }
}