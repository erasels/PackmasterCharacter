package thePackmaster.vfx.transmutationpack;

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
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.actions.transmutationpack.TransmuteCardAction;
import thePackmaster.cards.transmutationpack.AbstractHydrologistCard;

import java.util.HashMap;

public class TransmuteCardEffect extends AbstractGameEffect {
    private static final int MAX_CARD_COUNT = 8;
    private static final float MAX_LEFT_BOUND = 0.1f;
    private static final float MAX_RIGHT_BOUND = 0.9f;
    private static final float MAX_DOWN_BOUND = 0.2f;
    private static final float MAX_UP_BOUND = 0.8f;
    private static final float PARTICLE_SPAWN_HEIGHT = 100.0f;
    private static final float PARTICLE_SPAWN_WIDTH = 300.0f;
    private static final int PARTICLES_PER_SECOND = 60;
    private static final float DURATION_PER_PARTICLE = 1.0f / PARTICLES_PER_SECOND;
    private static final TextureRegion MASK = new TextureRegion(new Texture(SpireAnniversary5Mod.makePath("images/vfx/transmutationpack/TransmuteMask.png")), 512, 1024);
    private static final TextureRegion LINE = new TextureRegion(new Texture(SpireAnniversary5Mod.makePath("images/vfx/transmutationpack/TransmuteLine.png")), 512, 1024);
    private final HashMap<AbstractCard, TextureRegion> textureMap = new HashMap<>();
    private final HashMap<AbstractCard, AbstractCard> transmutedPairs;
    private final TransmuteCardAction action;
    private final CardGroup.CardGroupType targetGroup;
    private final FrameBuffer fb1;
    private final FrameBuffer fb2;
    private final FrameBuffer fb3;
    private float offsetPercent;
    private float particleTimer;

    public TransmuteCardEffect(HashMap<AbstractCard, AbstractCard> transmutedPairs, CardGroup.CardGroupType targetGroup, TransmuteCardAction action, float duration) {
        this.transmutedPairs = transmutedPairs;
        this.action = action;
        this.targetGroup = targetGroup;
        this.duration = duration;
        this.startingDuration = duration;
        fb1 = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
        fb2 = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
        fb3 = new FrameBuffer(Pixmap.Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false, false);
    }

    @Override
    public void render(SpriteBatch sb) {
        //generate partial card images for each hash pair, using the mask, and overlay them over the cards positions along with transmute line effect
        //first, for each card, establish a new texture by creating a 512x512 framebuffer, and render the card, followed by the mask, at 256x256
        for (AbstractCard keyCard : transmutedPairs.keySet()) {
            AbstractCard card = transmutedPairs.get(keyCard);
            AbstractCard copyCard = keyCard.makeStatEquivalentCopy();
            sb.end();

            fb1.begin();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glColorMask(true,true,true,true);
            sb.begin();

            //render both cards at 256 x 256 on the frame buffer, then capture the texture to be used as a mask
            sb.setColor(Color.WHITE);
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            setCardAttributes(card);
            card.render(sb);
            setCardAttributes(copyCard);
            copyCard.render(sb);

            sb.end();
            fb1.end();

            TextureRegion tmpMask = new TextureRegion(fb1.getColorBufferTexture());
            tmpMask.flip(false, true);

            fb2.begin();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glColorMask(true,true,true,true);
            sb.begin();

            //render the line at offset to be masked
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            sb.setColor(Color.WHITE);
            sb.draw(LINE, 0f, 0f - (512f * offsetPercent));

            //render the tmp mask (not at offset) to mask the line, then capture the texture to be rendered over the final product
            sb.setBlendFunction(0, GL20.GL_SRC_ALPHA);
            sb.setColor(new Color(1,1,1,1));
            sb.draw(tmpMask, 0f, 0f);

            sb.end();
            fb2.end();

            TextureRegion tmpLine = new TextureRegion(fb2.getColorBufferTexture());
            tmpLine.flip(false, true);

            fb3.begin();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
            Gdx.gl.glColorMask(true,true,true,true);
            sb.begin();

            //render the card again, this time to be masked.
            sb.setColor(Color.WHITE);
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            setCardAttributes(card);
            card.render(sb);

            //render the mask at offset
            sb.setBlendFunction(0, GL20.GL_SRC_ALPHA);
            sb.setColor(new Color(1,1,1,1));
            sb.draw(MASK, 0f, 0f - (512f * offsetPercent));

            //render the "water line"
            sb.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
            sb.setColor(Color.WHITE);
            sb.draw(tmpLine, 0f, 0f);

            sb.end();
            fb3.end();

            TextureRegion img = new TextureRegion(fb3.getColorBufferTexture());
            img.flip(false, true);
            textureMap.put(card, img);
            sb.begin();
        }
        //second, render the new textures to the key cards coordinates, angle, and scale. Iterate through limbo to make sure the masking cards have the same render order as the original cards.
        for (AbstractCard card : AbstractDungeon.player.limbo.group) {
            if (transmutedPairs.containsKey(card)) {
                TextureRegion img = textureMap.get(transmutedPairs.get(card));
                sb.draw(img,card.current_x - 256f,card.current_y - 256f,256f,256f, img.getRegionWidth(), img.getRegionHeight(),card.drawScale * Settings.scale,card.drawScale * Settings.scale, card.angle);
            }
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
            //Initialize: place the cards along the screen. Centered if only one card, but otherwise scattered similar to other game effects.
            AbstractDungeon.player.limbo.group.addAll(transmutedPairs.keySet());
            float cardCount = transmutedPairs.keySet().size();
            float alpha = Math.min(1.0f, (cardCount - 1) / ((float)MAX_CARD_COUNT - 1));
            float leftBound = Interpolation.circleOut.apply(0.5f, MAX_LEFT_BOUND, alpha);
            float rightBound = Interpolation.circleOut.apply(0.5f, MAX_RIGHT_BOUND, alpha);
            float downBound = Interpolation.circleOut.apply(0.5f, MAX_DOWN_BOUND, alpha);
            float upBound = Interpolation.circleOut.apply(0.5f, MAX_UP_BOUND, alpha);
            for (AbstractCard card : transmutedPairs.keySet()) {
                card.targetDrawScale = 1.0f;
                card.setAngle(0.0f, true);
                card.target_x = MathUtils.random(leftBound, rightBound) * Settings.WIDTH;
                card.target_y = MathUtils.random(downBound, upBound) * Settings.HEIGHT;
            }
            duration -= Gdx.graphics.getDeltaTime();
            CardCrawlGame.sound.play(SpireAnniversary5Mod.TRANSMUTATION_KEY);
        } else if (duration > 0.0f) {
            //Upkeep: control logic of where the mask is to be placed upon the card(s), based on duration of the effect/action
            duration -= Gdx.graphics.getDeltaTime();
            offsetPercent = duration / startingDuration;
            //generate particles based on card positions and offset percentage
            particleTimer += Gdx.graphics.getDeltaTime();
            while (particleTimer >= DURATION_PER_PARTICLE) {
                particleTimer -= DURATION_PER_PARTICLE;
                for (AbstractCard card : transmutedPairs.keySet()) {
                    float center_x = card.current_x;
                    float center_y = card.current_y - (512f * offsetPercent) + 256f; //start at bottom of card at 100%, end at top of card at 0%
                    float rotation = AbstractDungeon.miscRng.random(0.0f, 360.0f);
                    float scale = AbstractDungeon.miscRng.random(0.8f, 1.2f);
                    //calculate random coordinates within a bounding box
                    float x = center_x + (AbstractDungeon.miscRng.random(0.0f, PARTICLE_SPAWN_WIDTH * Settings.scale) - ((PARTICLE_SPAWN_WIDTH * Settings.scale) / 2));
                    if (x < card.hb.x + card.hb.height) {
                        float y = center_y + (AbstractDungeon.miscRng.random(0.0f, PARTICLE_SPAWN_HEIGHT * Settings.scale) - ((PARTICLE_SPAWN_HEIGHT * Settings.scale) / 2));
                        AbstractDungeon.topLevelEffectsQueue.add(new HydrologistParticle(AbstractHydrologistCard.Subtype.WATER, x, y, rotation, scale));
                    }
                }
            }
        } else {
            //then, when the effect completes, distribute the cards and signal the action to complete.
            if (targetGroup != null) {
                switch (targetGroup) {
                    case HAND:
                        for (AbstractCard card : transmutedPairs.keySet()) {
                            AbstractCard newCard = transmutedPairs.get(card);
                            copyCardPosition(card, newCard);
                            AbstractDungeon.player.limbo.removeCard(card);
                            AbstractDungeon.player.hand.addToHand(newCard);
                        }
                        AbstractDungeon.player.hand.refreshHandLayout();
                        break;
                    case DRAW_PILE:
                        for (AbstractCard card : transmutedPairs.keySet()) {
                            AbstractCard newCard = transmutedPairs.get(card);
                            copyCardPosition(card, newCard);
                            AbstractDungeon.player.limbo.removeCard(card);
                            AbstractDungeon.player.drawPile.moveToDeck(newCard, true);
                        }
                        break;
                    case DISCARD_PILE:
                        for (AbstractCard card : transmutedPairs.keySet()) {
                            AbstractCard newCard = transmutedPairs.get(card);
                            copyCardPosition(card, newCard);
                            AbstractDungeon.player.limbo.removeCard(card);
                            AbstractDungeon.player.discardPile.moveToDiscardPile(newCard);
                        }
                        break;
                    case EXHAUST_PILE:
                        for (AbstractCard card : transmutedPairs.keySet()) {
                            AbstractCard newCard = transmutedPairs.get(card);
                            copyCardPosition(card, newCard);
                            AbstractDungeon.player.limbo.removeCard(card);
                            AbstractDungeon.player.exhaustPile.moveToExhaustPile(newCard);
                        }
                        break;
                    default:
                        System.out.println("TransmuteCardEffect: How was this reached?");
                        break;
                }
            } else {
                for (AbstractCard card : transmutedPairs.keySet()) {
                    AbstractDungeon.player.limbo.removeCard(card);
                    copyCardPosition(card, transmutedPairs.get(card));
                    AbstractDungeon.player.limbo.addToTop(transmutedPairs.get(card));
                }
            }
            AbstractDungeon.player.hand.applyPowers();
            AbstractDungeon.player.hand.glowCheck();
            action.isDone = true;
            isDone = true;
        }
    }

    public static void copyCardPosition(AbstractCard original, AbstractCard target) {
        target.current_x = original.current_x;
        target.current_y = original.current_y;
        target.target_x = original.target_x;
        target.target_y = original.target_y;
        target.drawScale = original.drawScale;
        target.targetDrawScale = original.targetDrawScale;
        target.angle = original.angle;
        target.targetAngle = original.targetAngle;
        target.transparency = original.transparency;
        target.targetTransparency = original.targetTransparency;
    }

    @Override
    public void dispose() {
        fb1.dispose();
        fb2.dispose();
        fb3.dispose();
    }
}
