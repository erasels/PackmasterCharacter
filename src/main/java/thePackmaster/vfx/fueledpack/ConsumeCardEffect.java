package thePackmaster.vfx.fueledpack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;
import com.megacrit.cardcrawl.vfx.scene.TorchParticleXLEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;
import thePackmaster.vfx.transmutationpack.HydrologistParticle;

import java.util.ArrayList;
import java.util.HashMap;

public class ConsumeCardEffect extends AbstractGameEffect {
    private static final int MAX_CARD_COUNT = 8;
    private static final float MAX_LEFT_BOUND = 0.1f;
    private static final float MAX_RIGHT_BOUND = 0.9f;
    private static final float MAX_DOWN_BOUND = 0.2f;
    private static final float MAX_UP_BOUND = 0.8f;
    private static final float PARTICLE_SPAWN_HEIGHT = 100.0f;
    private static final float PARTICLE_SPAWN_WIDTH = 300.0f;
    private static final int PARTICLES_PER_SECOND = 150;
    private static final float DURATION_PER_PARTICLE = 1.0f / PARTICLES_PER_SECOND;
    private static final TextureRegion MASK = new TextureRegion(
            new Texture(SpireAnniversary5Mod.makePath("images/vfx/fueledpack/ConsumeMask.png")),
            512, 1024);
    private static final TextureRegion LINE = new TextureRegion(
            new Texture(SpireAnniversary5Mod.makePath("images/vfx/fueledpack/ConsumeLine.png")),
            512, 1024);
    private static final ArrayList<BufferWrapper> bufferCache = new ArrayList<>();
    private final HashMap<AbstractCard, AbstractCard> consumePairs;
    private float offsetPercent = 1.0f;
    private float particleTimer;

    public ConsumeCardEffect(HashMap<AbstractCard, AbstractCard> consumePairs, float duration) {
        this.consumePairs = consumePairs;
        this.duration = duration;
        this.startingDuration = duration;
        for (AbstractCard card : consumePairs.keySet()) {
            card.stopGlowing();
            card.transparency = card.targetTransparency = 0.0f;
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        // generate partial card images for each hash pair, using the mask, and overlay them over the cards positions
        // along with transmute line effect
        // first, for each card, establish a new texture by creating a 512x512 framebuffer, and render the card,
        // followed by the mask, at 256x256
        for (AbstractCard keyCard : consumePairs.keySet()) {
            AbstractCard newCard = consumePairs.get(keyCard);
            AbstractCard keyCopy = keyCard.makeStatEquivalentCopy();
            sb.end();

            FrameBuffer fb = getUnusedBuffer();
            fb.begin();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glColorMask(true,true,true,true);
            sb.begin();

            //render both cards at 256 x 256 on the frame buffer, then capture the texture to be used as a mask
            sb.setColor(Color.WHITE);
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            if (newCard != null) {
                setCardAttributes(newCard);
                newCard.render(sb);
            }
            setCardAttributes(keyCopy);
            keyCopy.render(sb);

            sb.end();
            fb.end();

            TextureRegion tmpMask = new TextureRegion(fb.getColorBufferTexture());
            tmpMask.flip(false, true);

            fb = getUnusedBuffer();
            fb.begin();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glColorMask(true,true,true,true);
            sb.begin();

            //render the line at offset to be masked
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            sb.setColor(Color.WHITE);
            sb.draw(LINE, 0f, 0f - (512f * offsetPercent));

            // render the mask made of both cards to mask the line,
            // then capture the texture to be rendered over the final product
            sb.setBlendFunction(0, GL20.GL_SRC_ALPHA);
            sb.setColor(new Color(1,1,1,1));
            sb.draw(tmpMask, 0f, 0f);

            sb.end();
            fb.end();

            TextureRegion tmpLine = new TextureRegion(fb.getColorBufferTexture());
            tmpLine.flip(false, true);

            //render the original card to be masked
            fb = getUnusedBuffer();
            fb.begin();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glColorMask(true,true,true,true);
            sb.begin();
            sb.setColor(Color.WHITE);
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            keyCopy.render(sb);

            //render the mask at offset
            sb.setBlendFunction(0, GL20.GL_ONE_MINUS_SRC_ALPHA);
            sb.setColor(new Color(1,1,1,1));
            sb.draw(MASK, 0f, 0f - (512f * offsetPercent));

            //render the "water line"
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            sb.setColor(Color.WHITE);
            sb.draw(tmpLine, 0f, 0f);

            sb.end();
            fb.end();

            TextureRegion img = new TextureRegion(fb.getColorBufferTexture());
            img.flip(false, true);

            //render the new card to be masked
            if (newCard != null) {
                fb = getUnusedBuffer();
                fb.begin();
                Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
                Gdx.gl.glColorMask(true, true, true, true);
                sb.begin();
                sb.setColor(Color.WHITE);
                sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                newCard.render(sb);

                //render the mask at offset
                sb.setBlendFunction(0, GL20.GL_SRC_ALPHA);
                sb.setColor(new Color(1, 1, 1, 1));
                sb.draw(MASK, 0f, 0f - (512f * offsetPercent));

                sb.end();
                fb.end();

                TextureRegion newImg = new TextureRegion(fb.getColorBufferTexture());
                newImg.flip(false, true);
                sb.begin();

                sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                sb.setColor(Color.WHITE);
                sb.draw(newImg,keyCard.current_x - 256f,keyCard.current_y - 256f,256f,256f,
                        newImg.getRegionWidth(), newImg.getRegionHeight(),keyCard.drawScale * Settings.scale,
                        keyCard.drawScale * Settings.scale, keyCard.angle);
                sb.end();
            }
            sb.begin();
            sb.draw(img,keyCard.current_x - 256f,keyCard.current_y - 256f,256f,256f,
                    img.getRegionWidth(), img.getRegionHeight(),keyCard.drawScale * Settings.scale,
                    keyCard.drawScale * Settings.scale, keyCard.angle);
            resetBuffers();
        }
    }

    private void setCardAttributes(AbstractCard card) {
        card.current_x = 256.0f;
        card.target_x = 256.0f;
        card.current_y = 256.0f;
        card.target_y = 256.0f;
        card.drawScale = 1.0f / Settings.scale;
        card.angle = 0.0f;
    }

    @Override
    public void update() {
        if (duration == startingDuration) {
            // Initialize: place the cards along the screen. Centered if only one card, but otherwise scattered similar
            // to other game effects.
            float cardCount = consumePairs.keySet().size();
            float alpha = Math.min(1.0f, (cardCount - 1) / ((float)MAX_CARD_COUNT - 1));
            float leftBound = Interpolation.circleOut.apply(0.5f, MAX_LEFT_BOUND, alpha);
            float rightBound = Interpolation.circleOut.apply(0.5f, MAX_RIGHT_BOUND, alpha);
            float downBound = Interpolation.circleOut.apply(0.5f, MAX_DOWN_BOUND, alpha);
            float upBound = Interpolation.circleOut.apply(0.5f, MAX_UP_BOUND, alpha);
            for (AbstractCard card : consumePairs.keySet()) {
                card.targetDrawScale = 1.0f;
                card.setAngle(0.0f, true);
                card.target_x = MathUtils.random(leftBound, rightBound) * Settings.WIDTH;
                card.target_y = MathUtils.random(downBound, upBound) * Settings.HEIGHT;
            }
            duration -= Gdx.graphics.getDeltaTime();
            CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);
        } else if (duration > 0.0f) {
            // Upkeep: control logic of where the mask is to be placed upon the card(s), based on duration of
            // the effect/action
            duration -= Gdx.graphics.getDeltaTime();
            offsetPercent = duration / startingDuration;
            //generate particles based on card positions and offset percentage
            particleTimer += Gdx.graphics.getDeltaTime();
            while (particleTimer >= DURATION_PER_PARTICLE) {
                particleTimer -= DURATION_PER_PARTICLE;
                for (AbstractCard card : consumePairs.keySet()) {
                    float center_x = card.current_x;
                    float center_y = card.current_y - (512f * offsetPercent) + 256f; //start at bottom of card at 100%, end at top of card at 0%
                    float rotation = AbstractDungeon.miscRng.random(0.0f, 360.0f);
                    float scale = AbstractDungeon.miscRng.random(0.8f, 1.2f);
                    //calculate random coordinates within a bounding box
                    float x = center_x + (AbstractDungeon.miscRng.random(0.0f, PARTICLE_SPAWN_WIDTH * Settings.scale) -
                            ((PARTICLE_SPAWN_WIDTH * Settings.scale) / 2));
                    if (x < card.hb.x + card.hb.height) {
                        float y = center_y + (AbstractDungeon.miscRng.random(0.0f, PARTICLE_SPAWN_HEIGHT * Settings.scale) -
                                ((PARTICLE_SPAWN_HEIGHT * Settings.scale) / 2));
                        AbstractDungeon.topLevelEffectsQueue.add(new TorchParticleXLEffect(
                                x,y + MathUtils.random(-10.0F, 10.0F) * Settings.scale));
                    }
                }
            }
        } else {
            isDone = true;
        }
        consumePairs.keySet().forEach(AbstractCard::update);
    }

    @Override
    public void dispose() {
        bufferCache.forEach(buffer -> buffer.buffer.dispose());
    }

    private void resetBuffers() {
        for (BufferWrapper bufferWrapper : bufferCache) {
            bufferWrapper.inUse = false;
        }
    }

    private FrameBuffer getUnusedBuffer() {
        for (BufferWrapper buffer : bufferCache) {
            if (!buffer.inUse) {
                buffer.inUse = true;
                return buffer.buffer;
            }
        }
        BufferWrapper newBuffer = new BufferWrapper();
        newBuffer.inUse = true;
        bufferCache.add(newBuffer);
        return newBuffer.buffer;
    }

    private static class BufferWrapper {
        private boolean inUse = false;
        private final FrameBuffer buffer = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), false, false);
    }
}
