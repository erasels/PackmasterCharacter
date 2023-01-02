package thePackmaster.vfx.summonspack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.util.TexLoader;

public class ElephantPieceEffect extends AbstractGameEffect {
    private static final float DURATION = 10.0F;
    private static final float FILE_WIDTH = 256;
    public static final float GRAVITY = 2700f;
    private final Texture img;

    private float x;
    private float y;

    private float vx;
    private float vy;
    private float vRot;

    public ElephantPieceEffect(String fileName, float x, float y) {
        img = TexLoader.getTexture(fileName);
        this.x = x;
        this.y = y;
        rotation = 0f;
        startingDuration = DURATION;
        duration = DURATION;
        scale = Settings.scale;
        renderBehind = false;
        vx = MathUtils.random(-1000f, 1000f);
        vy = MathUtils.random(200f, 1000f);
        vRot = MathUtils.random(-180f, 180f);
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        x += vx * delta;
        y += vy * delta;
        vy -= GRAVITY * delta;
        rotation += vRot * delta;

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0)
            isDone = true;
    }

    public void render(SpriteBatch sb) {
        sb.setColor(Color.WHITE.cpy());
        sb.setBlendFunction(770, 771);
        sb.draw(img, x - FILE_WIDTH/2F, y - FILE_WIDTH/2F, FILE_WIDTH/2F, FILE_WIDTH/2F,
                FILE_WIDTH, FILE_WIDTH, scale, scale, rotation, 0, 0, (int)FILE_WIDTH, (int)FILE_WIDTH,
                false, true);
    }

    public void dispose() {
    }
}
