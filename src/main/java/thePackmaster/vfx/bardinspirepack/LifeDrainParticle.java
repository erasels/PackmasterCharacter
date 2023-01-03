package thePackmaster.vfx.bardinspirepack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.CatmullRomSpline;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.DamageImpactLineEffect;

import java.util.ArrayList;

public class LifeDrainParticle extends AbstractGameEffect
{
    private AtlasRegion img;
    private CatmullRomSpline<Vector2> crs = new CatmullRomSpline<>();
    private ArrayList<Vector2> controlPoints = new ArrayList<>();
    private static final int TRAIL_ACCURACY = 60;
    private Vector2[] points = new Vector2[TRAIL_ACCURACY];
    private Vector2 pos, target;

    // Speed-related variables
    private float currentSpeed;
    private static final float MAX_VELOCITY = 4000f * Settings.scale;
    private static final float VELOCITY_RAMP_RATE = 3000f * Settings.scale;
    private static final float DST_THRESHOLD = 42f * Settings.scale;

    // Angle and turn-radius related variables
    private float rotation;
    private boolean rotateClockwise;
    private boolean stopRotating = false;
    private boolean facingLeft;
    private float rotationRate;

    public LifeDrainParticle(float sX, float sY, float tX, float tY, boolean facingLeft)
    {
        img = ImageMaster.GLOW_SPARK_2;
        pos = new Vector2(sX, sY);

        if (!facingLeft) {
            target = new Vector2(tX + DST_THRESHOLD, tY);
        } else {
            target = new Vector2(tX - DST_THRESHOLD, tY);
        }
        this.facingLeft = facingLeft;
        crs.controlPoints = new Vector2[1];
        rotateClockwise = true;//MathUtils.randomBoolean();
        rotation = MathUtils.random(190, 350);
        controlPoints.clear();
        rotationRate = MathUtils.random(600f, 650f) * Settings.scale;
        currentSpeed = 1000f * Settings.scale;
        color = Color.valueOf("d4b926");
        color.a = 0.6f;
        duration = 0.7f;
        scale = 0.5f * Settings.scale;
        renderBehind = MathUtils.randomBoolean();
    }

    public void update()
    {
        updateMovement();
    }

    private void updateMovement()
    {
        // Calculate the correct angle we want to get to
        Vector2 tmp = new Vector2(pos.x - target.x, pos.y - target.y);
        tmp.nor();
        float targetAngle = tmp.angle();
        rotationRate += Gdx.graphics.getDeltaTime() * 2000f;
        scale += Gdx.graphics.getDeltaTime() * 3f * Settings.scale;

        // Update our current angle to rotate towards the perfect angle
        if (!stopRotating) {
            if (rotateClockwise) {
                rotation += Gdx.graphics.getDeltaTime() * rotationRate;
            } else {
                rotation -= Gdx.graphics.getDeltaTime() * rotationRate;
                if (rotation < 0f) {
                    rotation += 360f;
                }
            }

            rotation = rotation % 360f;

            if (!stopRotating) {
                if (Math.abs(rotation - targetAngle) < Gdx.graphics.getDeltaTime() * rotationRate) {
                    rotation = targetAngle;
                    stopRotating = true;
                }
            }
        }

        // Update our movement vector to be the ACTUAL angle of our soul
        tmp.setAngle(rotation);

        // Apply speed and move our position
        tmp.x *= Gdx.graphics.getDeltaTime() * currentSpeed;
        tmp.y *= Gdx.graphics.getDeltaTime() * currentSpeed;
        pos.sub(tmp);

        if (stopRotating) {
            currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 3f;
        } else {
            currentSpeed += Gdx.graphics.getDeltaTime() * VELOCITY_RAMP_RATE * 1.5f;
        }
        if (currentSpeed > MAX_VELOCITY) {
            currentSpeed = MAX_VELOCITY;
        }

        // Set overshot souls to isDone
        if (target.dst(pos) < DST_THRESHOLD) {
            for (int i = 0; i < 5; i++) {
                if (facingLeft) {
                    AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(target.x + DST_THRESHOLD, target.y));
                } else {
                    AbstractDungeon.effectsQueue.add(new DamageImpactLineEffect(target.x - DST_THRESHOLD, target.y));
                }
            }
            CardCrawlGame.sound.playAV("HEAL_1", MathUtils.random(0.6f, 0.9f), 0.5f);
            isDone = true;
        }

        if (!controlPoints.isEmpty()) {
            if (!controlPoints.get(0).equals(pos)) {
                controlPoints.add(pos.cpy());
            }
        } else {
            controlPoints.add(pos.cpy());
        }

        if (controlPoints.size() > 3) {
            Vector2[] vec2Array = new Vector2[0];
            crs.set(controlPoints.toArray(vec2Array), false);
            for (int i = 0; i < TRAIL_ACCURACY; i++) {
                points[i] = new Vector2();
                crs.valueAt(points[i], ((float) i) / ((float) TRAIL_ACCURACY - 1));
            }
        }

        if (controlPoints.size() > 10) {
            controlPoints.remove(0);
        }

        duration -= Gdx.graphics.getDeltaTime();
        if (duration < 0) {
            isDone = true;
        }
    }

    public void render(SpriteBatch sb)
    {
        if (!isDone) {
            sb.setColor(color);
            float scaleCpy = scale;
            for (int i = points.length - 1; i > 0; i--) {
                if (points[i] != null) {
                    sb.draw(
                            img,
                            points[i].x - img.packedWidth / 2f,
                            points[i].y - img.packedHeight / 2f,
                            img.packedWidth / 2f,
                            img.packedHeight / 2f,
                            img.packedWidth,
                            img.packedHeight,
                            scaleCpy,
                            scaleCpy,
                            rotation);
                    scaleCpy *= 0.98f;
                }
            }
        }
    }

    @Override
    public void dispose()
    {
    }
}
