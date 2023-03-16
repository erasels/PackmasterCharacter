package thePackmaster.ui;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import thePackmaster.util.TexLoader;

public class RatingDarkStar extends AbstractCustomIcon {
    public static final String ID = RatingDarkStar.class.getSimpleName();
    private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingDarkStar.class.getSimpleName() + ".png");

    public RatingDarkStar() {
        super(ID, iconTexture);
    }
}