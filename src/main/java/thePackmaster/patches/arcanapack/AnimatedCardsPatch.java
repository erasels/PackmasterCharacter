package thePackmaster.patches.arcanapack;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CtBehavior;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.util.TexLoader;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimatedCardsPatch {
    private static final Logger logger = LogManager.getLogger("AnimatedCards");

    public static void loadFrames(CustomCard c, int frameCount, float frameRate) {
        loadFrames(c, c.textureImg, frameCount, frameRate);
    }
    public static void loadFrames(AbstractCard c, String animationTexture, int frameCount, float frameRate)
    {
        try {
            AnimatedCardsPatch.load(c, frameCount, frameRate, animationTexture);

            String img = AbstractPackmasterCard.getCardTextureString("TheFoolStatic", AbstractCard.CardType.ATTACK);
            if (c instanceof CustomCard) {
                ((CustomCard) c).textureImg = img;
                ((CustomCard) c).loadCardImage(((CustomCard) c).textureImg);
            }
            else {
                Texture t;
                if (CustomCard.imgMap.containsKey(img)) {
                    t = CustomCard.imgMap.get(img);
                } else {
                    t = ImageMaster.loadImage(img);
                    t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                    CustomCard.imgMap.put(img, t);
                }

                int tw = t.getWidth();
                int th = t.getHeight();
                c.portrait = c.jokePortrait = new TextureAtlas.AtlasRegion(t, 0, 0, tw, th);
            }
        }
        catch (Exception e)
        {
            logger.error("Failed to load animated card image for " + c.cardID + ".");
        }
    }

    private static void load(AbstractCard c, int frameCount, float frameRate, String frames)
    {
        if (!AnimationInfo.cardFrames.containsKey(c.cardID))
        {
            //set framerate
            AnimationInfo.frameRate.put(c.cardID, frameRate);

            //check for portrait animation

            if (!CustomCard.imgMap.containsKey(frames))
            {
                CustomCard.imgMap.put(frames, ImageMaster.loadImage(frames));
            }

            int endingIndex = frames.lastIndexOf(".");

            String portrait = frames.substring(0, endingIndex) + "_p" + frames.substring(endingIndex); //test for portrait animation
            if (TexLoader.testTexture(portrait))
            {
                AnimationInfo.portraitFramesPath.put(c.cardID, portrait);
                AnimationInfo.cardPortraitFrames.put(c.cardID, null);
            }

            //load atlas regions and frame counts
            Texture cardFramesTexture = CustomCard.imgMap.get(frames);
            int columnCount = cardFramesTexture.getWidth() / 250;
            int rowCount = cardFramesTexture.getHeight() / 190;
            TextureAtlas.AtlasRegion[] frameRegions = new TextureAtlas.AtlasRegion[frameCount];

            int currentFrame = 0;
            for (int y = 0; y < rowCount; ++y)
            {
                for (int x = 0; x < columnCount; ++x)
                {
                    frameRegions[currentFrame] = new TextureAtlas.AtlasRegion(cardFramesTexture, x * 250, y * 190, 250, 190);
                    currentFrame++;

                    if (currentFrame >= frameCount)
                    {
                        break;
                    }
                }
                if (currentFrame >= frameCount)
                {
                    break;
                }
            }

            AnimationInfo.cardFrames.put(c.cardID, frameRegions);
        }


        if (AnimationInfo.cardFrames.containsKey(c.cardID))
        {
            AnimationInfo.isAnimated.set(c, true);
            AnimationInfo.frameTime.set(c, frameRate);
            AnimationInfo.currentFrame.set(c, 0);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = SpirePatch.CLASS
    )
    public static class AnimationInfo
    {
        public static HashMap<String, TextureAtlas.AtlasRegion[]> cardFrames = new HashMap<>();
        public static HashMap<String, TextureAtlas.AtlasRegion[]> cardPortraitFrames = new HashMap<>();
        public static ArrayList<String> loadedPortraits = new ArrayList<>();
        public static HashMap<String, String> portraitFramesPath = new HashMap<>();
        public static HashMap<String, Float> frameRate = new HashMap<>();

        public static SpireField<Boolean> isAnimated = new SpireField<>(()->false);

        public static SpireField<Float> frameTime = new SpireField<>(()->0.0f);
        public static SpireField<Integer> currentFrame = new SpireField<>(()->0);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderPortrait"
    )
    public static class renderAnimated
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "drawX", "drawY" }
        )
        public static SpireReturn<?> altRender(AbstractCard __instance, SpriteBatch sb, float drawX, float drawY)
        {
            if (AnimationInfo.isAnimated.get(__instance))
            {
                sb.draw(AnimationInfo.cardFrames.get(__instance.cardID)[AnimationInfo.currentFrame.get(__instance)], drawX, drawY + 72.0F, 125.0F, 23.0F, 250.0F, 190.0F, __instance.drawScale * Settings.scale, __instance.drawScale * Settings.scale, __instance.angle);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                ArrayList<Matcher> prevMatches = new ArrayList<>();
                prevMatches.add(
                        new Matcher.MethodCallMatcher(SpriteBatch.class,
                                "setColor"));

                Matcher finalMatcher = new Matcher.MethodCallMatcher(SpriteBatch.class, "draw");
                return LineFinder.findAllInOrder(ctMethodToPatch, prevMatches, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "renderPortrait"
    )
    public static class renderSingleCardAnimated
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "card" }
        )
        public static SpireReturn<?> altRender(SingleCardViewPopup __instance, SpriteBatch sb, AbstractCard card)
        {
            if (!card.isLocked && AnimationInfo.isAnimated.get(card))
            {
                if (AnimationInfo.cardPortraitFrames.get(card.cardID) == null)
                {
                    clearPortraitFrames();
                    loadPortraitFrames(card);
                }
                sb.draw(AnimationInfo.cardPortraitFrames.get(card.cardID)[AnimationInfo.currentFrame.get(card)], (float)Settings.WIDTH / 2.0F - 250.0F, (float)Settings.HEIGHT / 2.0F - 190.0F + 136.0F * Settings.scale, 250.0f, 190.0f, 500.0f, 380.0f, Settings.scale, Settings.scale, 0.0f);

                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractCard.class, "isLocked");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "renderInLibrary"
    )
    public static class SaveLibraryFrames
    {
        @SpireInsertPatch(
                locator = Locator.class
        )
        public static SpireReturn<?> altRender(AbstractCard __instance, SpriteBatch sb)
        {
            if (AnimationInfo.isAnimated.get(__instance))
            {
                AbstractCard copy = __instance.makeCopy();
                copy.current_x = __instance.current_x;
                copy.current_y = __instance.current_y;
                copy.drawScale = __instance.drawScale;
                copy.upgrade();
                copy.displayUpgrades();
                AnimationInfo.frameTime.set(copy, AnimationInfo.frameTime.get(__instance));
                AnimationInfo.currentFrame.set(copy, AnimationInfo.currentFrame.get(__instance));
                copy.render(sb);
                return SpireReturn.Return(null);
            }
            return SpireReturn.Continue();
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "makeCopy");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "update"
    )
    public static class UpdateAnimation
    {
        @SpirePostfixPatch
        public static void updateAnim(AbstractCard __instance)
        {
            if (AnimationInfo.isAnimated.get(__instance))
            {
                AnimationInfo.frameTime.set(__instance, AnimationInfo.frameTime.get(__instance) - Gdx.graphics.getDeltaTime());

                if (AnimationInfo.frameTime.get(__instance) <= 0.0f)
                {
                    AnimationInfo.frameTime.set(__instance, AnimationInfo.frameRate.get(__instance.cardID));
                    AnimationInfo.currentFrame.set(__instance, (AnimationInfo.currentFrame.get(__instance) + 1) % AnimationInfo.cardFrames.get(__instance.cardID).length);
                }
            }
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "update"
    )
    public static class UpdateSingleViewAnimation
    {
        @SpireInsertPatch(
                rloc = 0,
                localvars = { "card" }
        )
        public static void updateAnim(SingleCardViewPopup __instance, AbstractCard card)
        {
            if (AnimationInfo.isAnimated.get(card))
            {
                AnimationInfo.frameTime.set(card, AnimationInfo.frameTime.get(card) - Gdx.graphics.getDeltaTime());

                if (AnimationInfo.frameTime.get(card) <= 0.0f)
                {
                    AnimationInfo.frameTime.set(card, AnimationInfo.frameRate.get(card.cardID));
                    AnimationInfo.currentFrame.set(card, (AnimationInfo.currentFrame.get(card) + 1) % AnimationInfo.cardFrames.get(card.cardID).length);
                }
            }
        }
    }

    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "render"
    )
    public static class SaveSingleCardViewFrames
    {
        @SpireInsertPatch(
                locator = Locator.class,
                localvars = { "card", "copy" }
        )
        public static void transferFrames(SingleCardViewPopup __instance, SpriteBatch sb, AbstractCard card, AbstractCard copy)
        {
            if (AnimationInfo.isAnimated.get(card))
            {
                AnimationInfo.frameTime.set(copy, AnimationInfo.frameTime.get(card));
                AnimationInfo.currentFrame.set(copy, AnimationInfo.currentFrame.get(card));
            }
        }

        private static class Locator extends SpireInsertLocator
        {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception
            {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractCard.class, "upgrade");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }


    private static void loadPortraitFrames(AbstractCard c)
    {
        //load portrait info
        if (AnimationInfo.portraitFramesPath.containsKey(c.cardID))
        {
            Texture cardFramesTexture = ImageMaster.loadImage(AnimationInfo.portraitFramesPath.get(c.cardID));
            int columnCount = cardFramesTexture.getWidth() / 500;
            int rowCount = cardFramesTexture.getHeight() / 380;
            int frameCount = AnimationInfo.cardFrames.get(c.cardID).length;
            TextureAtlas.AtlasRegion[] portraitFrameRegions = new TextureAtlas.AtlasRegion[frameCount];

            int currentFrame = 0;
            for (int y = 0; y < rowCount; ++y)
            {
                for (int x = 0; x < columnCount; ++x)
                {
                    portraitFrameRegions[currentFrame] = new TextureAtlas.AtlasRegion(cardFramesTexture, x * 500, y * 380, 500, 380);
                    currentFrame++;

                    if (currentFrame >= frameCount)
                    {
                        break;
                    }
                }
                if (currentFrame >= frameCount)
                {
                    break;
                }
            }

            AnimationInfo.cardPortraitFrames.put(c.cardID, portraitFrameRegions);
            AnimationInfo.loadedPortraits.add(c.cardID);
        }
    }
    private static void clearPortraitFrames()
    {
        for (String id : AnimationInfo.loadedPortraits)
        {
            if (AnimationInfo.cardPortraitFrames.get(id) != null && AnimationInfo.cardPortraitFrames.get(id).length > 0)
            {
                AnimationInfo.cardPortraitFrames.get(id)[0].getTexture().dispose();
            }
            AnimationInfo.cardPortraitFrames.put(id, null);
        }
        AnimationInfo.loadedPortraits.clear();
    }
}
