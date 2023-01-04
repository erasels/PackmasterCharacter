package thePackmaster.vfx.aggressionpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class WanderingFire {
    private static final float HIDE_CHANCE = 0.3F;
    private static final float HIDE_DURATION = 3.0F;
    private static final float STILL_MIN_DURATION = 2.0F;
    private static final float STILL_MAX_DURATION = 4.0F;
    private static final float LINE_MIN_DURATION = 5.0F;
    private static final float LINE_MAX_DURATION = 6.0F;
    private static final float SPIRAL_MIN_DURATION = 3.0F;
    private static final float SPIRAL_MAX_DURATION = 6.0F;

    public boolean hidden;
    private float x;
    private float y;
    private Color color;
    private boolean transparent;
    private float starting_x;
    private float starting_y;
    private float destination_x;
    private float destination_y;
    private float travelDuration;
    private float startingTravelDuration;
    private MovementPattern movementPattern;
    private float control_x1;
    private float control_y1;
    private float spiralRotations;
    private float spiralSize;
    private boolean spiralXOrientation;
    private boolean spiralYOrientation;
    private boolean first;
    private float particleTimer = 0.0F;
    private static final float PARTICLE_INTERVAL = 0.06F;

    public WanderingFire(float x, float y) {
        this.x = x;
        this.y = y;
        this.updateColor();
        this.hidden = true;
        this.first = true;
        this.movementPattern = MovementPattern.Still;
    }

    public void update() {
        this.updateMovement();
        this.particleTimer -= Gdx.graphics.getDeltaTime();
        if (this.particleTimer < 0.0F) {
            AbstractDungeon.effectList.add(new ColoredGhostlyFireEffect(this.x, this.y, color, this.transparent || this.hidden));
            this.particleTimer = PARTICLE_INTERVAL;
        }
    }

    private void updateColor() {
        this.color = new Color(-16776961);
        this.transparent = true;
    }

    private void updateMovement() {
        if (this.travelDuration > 0.0F) {
            float p = (this.startingTravelDuration - this.travelDuration) / this.startingTravelDuration;
            switch (this.movementPattern) {
                case Hide:
                    break;
                case Still:
                case Line:
                    this.x = this.starting_x + (this.destination_x - this.starting_x) * p;
                    this.y = this.starting_y + (this.destination_y - this.starting_y) * p;
                    break;
                case Arc:
                    this.x = (1 - p) * (1 - p) * this.starting_x + 2 * (1 - p) * p * this.control_x1 + p * p * this.destination_x;
                    this.y = (1 - p) * (1 - p) * this.starting_y + 2 * (1 - p) * p * this.control_y1 + p * p * this.destination_y;
                    break;
                case Spiral:
                    double radians = this.spiralRotations * 2 * Math.PI * p;
                    double xd = (this.spiralXOrientation ? 1.0 : -1.0) * this.spiralSize * radians * Math.cos(radians) * Settings.scale;
                    double yd = (this.spiralYOrientation ? 1.0 : -1.0) * this.spiralSize * radians * Math.sin(radians) * Settings.scale;
                    this.x = this.starting_x + (float)xd;
                    this.y = this.starting_y + (float)yd;
                    break;
            }
            this.travelDuration -= Gdx.graphics.getDeltaTime();
        }
        else {
            this.hidden = false;
            MovementPattern newMovementPattern = this.getMovementPattern();
            switch (newMovementPattern) {
                case Hide:
                    this.hide();
                    break;
                case Still:
                    this.setDestination(this.x, this.y, MathUtils.random(STILL_MIN_DURATION, STILL_MAX_DURATION));
                    break;
                case Line:
                case Arc:
                    Coordinate d = this.getNewDestination();
                    if (d != null) {
                        this.setDestination(d.x, d.y, MathUtils.random(LINE_MIN_DURATION, LINE_MAX_DURATION));
                        float xd = d.x - this.x;
                        float yd = d.y - this.y;
                        double curveScale = MathUtils.random(0.5F, 1.5F);
                        double dist = Math.sqrt((xd * xd + yd * yd)/2) * curveScale;
                        double orientation = MathUtils.randomBoolean() ? 1.0 : -1.0;
                        double angle = Math.atan2(yd, xd) + orientation * Math.PI / 4.0;
                        this.control_x1 = (float)(this.x + dist * Math.cos(angle));
                        this.control_y1 = (float)(this.y + dist * Math.sin(angle));
                    }
                    else {
                        newMovementPattern = MovementPattern.Hide;
                        this.hide();
                    }
                    break;
                case Spiral:
                    this.starting_x = this.x;
                    this.starting_y = this.y;
                    this.travelDuration = MathUtils.random(SPIRAL_MIN_DURATION, SPIRAL_MAX_DURATION);
                    this.startingTravelDuration = this.travelDuration;
                    this.spiralRotations = 3.0F * this.travelDuration / SPIRAL_MAX_DURATION;
                    this.spiralSize = MathUtils.random(1.0F, 3.0F);
                    this.spiralXOrientation = MathUtils.randomBoolean();
                    this.spiralYOrientation = MathUtils.randomBoolean();
                    break;
                default:
                    throw new RuntimeException("Unrecognized movement pattern: " + newMovementPattern);
            }
            this.movementPattern = newMovementPattern;
            this.first = false;
        }
    }

    private void hide() {
        this.hidden = true;
        this.x = this.getRandomXInRange();
        this.y = this.getRandomYInRange();
        this.setDestination(this.x, this.y, HIDE_DURATION);
        this.updateColor();
    }

    private void setDestination(float x, float y, float travelDuration) {
        this.starting_x = this.x;
        this.starting_y = this.y;
        this.destination_x = x;
        this.destination_y = y;
        this.travelDuration = travelDuration;
        this.startingTravelDuration = travelDuration;
    }

    private MovementPattern getMovementPattern() {
        if (!this.first && MathUtils.randomBoolean(HIDE_CHANCE)) {
            return MovementPattern.Hide;
        }
        int roll = MathUtils.random(99);
        if (roll < 10) {
            return MovementPattern.Still;
        }
        else if (roll < 20 && this.movementPattern != MovementPattern.Spiral) {
            return MovementPattern.Spiral;
        }
        else if (roll < 40) {
            return MovementPattern.Line;
        }
        else {
            return MovementPattern.Arc;
        }
    }

    private Coordinate getNewDestination() {
        float x;
        float y;
        int i = 0;
        // We want movement to look smooth, so we consider a selection of random new destinations, looking for one that
        // requires at least some movement (so that the fire keeps wandering) and that doesn't change our angle of movement
        // by too much (so that the fire's movement looks smooth).
        // If we don't find a new destination that meets our criteria in 100 tries, we return null and the fire hides.
        // Essentially, this makes fires increasingly likely to hide when they reach edges/corners of the range
        while (i < 100) {
            x = this.getRandomXInRange();
            y = this.getRandomYInRange();
            float xd = x - this.x;
            float yd = y - this.y;
            double dist = Math.sqrt(xd * xd + yd * yd);
            double distRequirement = 30.0F;
            boolean distOkay = dist >= distRequirement;
            double angle = Math.atan2(yd, xd);
            double previousAngle = Math.atan2(this.y - this.starting_y, this.x - this.starting_x);
            double ad = Math.min(Math.abs(angle - previousAngle), 2.0 * Math.PI - Math.abs(angle - previousAngle));
            double angleTolerance = Math.PI / 2.0;
            boolean angleOkay = !this.hasStartAndEndPoints(this.movementPattern) || Math.abs(ad) < angleTolerance;
            if (distOkay && angleOkay) {
                return new Coordinate(x, y);
            }
            i++;
        }
        return null;
    }

    private float getRandomXInRange() {
        return AbstractDungeon.player.hb.cX + MathUtils.random(-AbstractDungeon.player.hb.width / 2.0F - 30.0F * Settings.scale, AbstractDungeon.player.hb.width / 2.0F + 30.0F * Settings.scale);
    }

    private float getRandomYInRange() {
        return AbstractDungeon.player.hb.cY + MathUtils.random(-AbstractDungeon.player.hb.height / 2.0F - -10.0F * Settings.scale, AbstractDungeon.player.hb.height / 2.0F - 10.0F * Settings.scale);
    }

    private boolean hasStartAndEndPoints(MovementPattern mp) {
        switch (mp) {
            case Line:
            case Arc:
                return true;
            default:
                return false;
        }
    }

    private static class Coordinate {
        public final float x;
        public final float y;
        public Coordinate(float x, float y) {
            this.x = x;
            this.y = y;
        }
    }

    public enum MovementPattern {
        Hide,
        Still,
        Line,
        Arc,
        Spiral
    }
}
