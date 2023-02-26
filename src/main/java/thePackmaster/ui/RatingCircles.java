package thePackmaster.ui;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;
import thePackmaster.util.TexLoader;


public class RatingCircles {
    public static class RatingCircle1 extends AbstractCustomIcon {
        public static final String ID = RatingCircle1.class.getSimpleName();
        private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingCircle1.class.getSimpleName() + ".png");

        public RatingCircle1() {
            super(ID, iconTexture);
        }
    }

    public static class RatingCircle2 extends AbstractCustomIcon {
        public static final String ID = RatingCircle2.class.getSimpleName();
        private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingCircle2.class.getSimpleName() + ".png");

        public RatingCircle2() {
            super(ID, iconTexture);
        }
    }

    public static class RatingCircle3 extends AbstractCustomIcon {
        public static final String ID = RatingCircle3.class.getSimpleName();
        private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingCircle3.class.getSimpleName() + ".png");

        public RatingCircle3() {
            super(ID, iconTexture);
        }
    }

    public static class RatingCircle4 extends AbstractCustomIcon {
        public static final String ID = RatingCircle4.class.getSimpleName();
        private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingCircle4.class.getSimpleName() + ".png");

        public RatingCircle4() {
            super(ID, iconTexture);
        }
    }

    public static class RatingCircle5 extends AbstractCustomIcon {
        public static final String ID = RatingCircle5.class.getSimpleName();
        private static final Texture iconTexture = TexLoader.getTexture("anniv5Resources/images/ui/" + RatingCircle5.class.getSimpleName() + ".png");

        public RatingCircle5() {
            super(ID, iconTexture);
        }
    }
}
