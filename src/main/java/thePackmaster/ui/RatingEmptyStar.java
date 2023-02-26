package thePackmaster.ui;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import thePackmaster.util.TexLoader;

public class RatingEmptyStar extends AbstractCustomIcon {
    public static final String ID = RatingEmptyStar.class.getSimpleName();
    private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingEmptyStar.class.getSimpleName() + ".png");

    public RatingEmptyStar() {
        super(ID, iconTexture);
    }
}