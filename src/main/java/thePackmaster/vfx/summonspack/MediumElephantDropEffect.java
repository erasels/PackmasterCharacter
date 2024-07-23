package thePackmaster.vfx.summonspack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.makePath;

public class MediumElephantDropEffect extends AbstractGameEffect {
    public static final float DURATION = 0.4F;
    private static String IMG_PATH;
    private static Texture IMG;
    private static float ELEPHANT_WIDTH;
    private static float ELEPHANT_HEIGHT;
    private static float START_Y;
    private static float FLOOR_Y;
    private static float HIT_X;

    private float y;

    public MediumElephantDropEffect() {
        startingDuration = DURATION;
        duration = DURATION;
        scale = Settings.scale;
        renderBehind = false;
        if (IMG == null)
            setStatic();
        y = START_Y;
    }

    public void update() {
        y = FLOOR_Y + (START_Y - FLOOR_Y) * duration / DURATION;

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0.0F) {
            AbstractDungeon.effectsQueue.add(new ElephantSplatEffect(HIT_X, FLOOR_Y));
            isDone = true;
            y = FLOOR_Y;
        }
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(IMG, HIT_X - ELEPHANT_WIDTH/2F, y - ELEPHANT_HEIGHT/2F, ELEPHANT_WIDTH/2F, ELEPHANT_HEIGHT/2F,
                ELEPHANT_WIDTH, ELEPHANT_HEIGHT, scale, scale, rotation, 0, 0, (int)ELEPHANT_WIDTH, (int)ELEPHANT_HEIGHT,
                false, true);
    }

    public void dispose() {
    }

    public static void setStatic() {
        IMG_PATH = makePath("images/vfx/summonspack/elephant/Elephant.png");
        IMG = ImageMaster.loadImage(IMG_PATH);
        ELEPHANT_WIDTH = IMG.getWidth();
        ELEPHANT_HEIGHT = IMG.getHeight();
        START_Y = (1080f + ELEPHANT_HEIGHT/2f)* Settings.yScale;
        FLOOR_Y = 400f * Settings.scale;
        HIT_X = 1250f * Settings.scale;
    }
}
