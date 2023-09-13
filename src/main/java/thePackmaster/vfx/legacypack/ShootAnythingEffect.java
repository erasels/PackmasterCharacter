package thePackmaster.vfx.legacypack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class ShootAnythingEffect extends AbstractGameEffect {

    private static final float DROP_ON_HEAD_STARTING_DURATION = 0.40f;
    private static final float SHOOT_ANYTHING_STARTING_DURATION = 0.45f;

    private Projectile projectile;
    private boolean dropOnHead;
    private AbstractCreature abstractCreature;
    private Texture texture;
    private int count;

    public ShootAnythingEffect(AbstractCreature abstractCreature, Texture texture, boolean dropOnHead, int count) {
        this.abstractCreature = abstractCreature;
        this.texture = texture;
        this.dropOnHead = dropOnHead;
        this.count = count;
        this.projectile = new Projectile(this.count);
    }

    @Override
    public void render(SpriteBatch sb) {
        projectile.render(sb);
        sb.setColor(Color.WHITE);
    }

    @Override
    public void update() {
        boolean hit = projectile.update();

        if (hit) {
            this.isDone = true;
        }
    }

    public void dispose() {
        //empty
    }

    class Projectile {
        private float x;
        private float y;
        private float targetX;
        private float targetY;
        private float rotation;
        private boolean hit;
        private float duration;
        private float startingDuration;

        Projectile(int count) {
            this.startingDuration = dropOnHead ? getDropOnHeadStartingDuration(count) : SHOOT_ANYTHING_STARTING_DURATION;

            targetX = abstractCreature.hb.cX + MathUtils.random(abstractCreature.hb.width / 2) - abstractCreature.hb.width * 1 / 4;
            targetY = abstractCreature.hb.cY + MathUtils.random(abstractCreature.hb.height / 2) - abstractCreature.hb.height * 1 / 4;

            if (dropOnHead) {
                x = abstractCreature.hb.cX;
                y = abstractCreature.hb.cY + 1000;
            } else {
                x = AbstractDungeon.player.hb.cX;
                y = AbstractDungeon.player.hb.cY;
            }

            hit = false;
            duration = startingDuration;

            rotation = (MathUtils.random(-30.0F, 30.0F));
        }

        private float getDropOnHeadStartingDuration(int count) {
            float limitedCounter = (float) Math.min(count, 7);
            return DROP_ON_HEAD_STARTING_DURATION * (1f - (limitedCounter / (limitedCounter + 7)));
        }

        void render(SpriteBatch sb) {
            sb.setColor(1F, 1F, 1F, 1F);
            sb.draw(texture, (this.x - (texture.getWidth() / 2F)), (this.y - (texture.getHeight() / 2F)), texture.getWidth() / 2F, texture.getHeight() / 2F, texture.getWidth(), texture.getHeight(), Settings.scale, Settings.scale, this.rotation, 0, 0, texture.getWidth(), texture.getHeight(), false, false);
        }

        public boolean update() {
            if (!hit) {
                float progress = (this.startingDuration - this.duration) / this.startingDuration;

                if (dropOnHead) {
                    x = abstractCreature.hb.cX + (targetX - abstractCreature.hb.cX) * progress;
                    y = (abstractCreature.hb.cY + 1000) + (targetY - (abstractCreature.hb.cY + 1000)) * progress;
                } else {
                    x = AbstractDungeon.player.hb.cX + (targetX - AbstractDungeon.player.hb.cX) * progress;
                    y = AbstractDungeon.player.hb.cY + (targetY - AbstractDungeon.player.hb.cY) * progress;
                }

                this.duration -= Gdx.graphics.getDeltaTime();
                if (duration < 0) {
                    hit = true;
                }
            }
            return hit;
        }
    }
}