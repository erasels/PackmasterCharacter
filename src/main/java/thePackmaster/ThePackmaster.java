package thePackmaster;

import basemod.abstracts.CustomEnergyOrb;
import basemod.abstracts.CustomPlayer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.*;
import com.esotericsoftware.spine.attachments.RegionAttachment;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.cutscenes.CutscenePanel;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.localization.CharacterStrings;
import com.megacrit.cardcrawl.screens.CharSelectInfo;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import thePackmaster.cards.Cardistry;
import thePackmaster.cards.Defend;
import thePackmaster.cards.Rummage;
import thePackmaster.cards.Strike;
import thePackmaster.hats.HatMenu;
import thePackmaster.hats.HatsManager;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.relics.HandyHaversack;
import thePackmaster.skins.AbstractSkin;
import thePackmaster.util.TexLoader;
import thePackmaster.vfx.VictoryConfettiEffect;
import thePackmaster.vfx.VictoryGlow;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static thePackmaster.SpireAnniversary5Mod.*;
import static thePackmaster.ThePackmaster.Enums.PACKMASTER_RAINBOW;

public class ThePackmaster extends CustomPlayer {
    public static final String SHOULDER1 = "shoulder.png";
    public static final String SHOULDER2 = "shoulder2.png";
    public static final String CORPSE = "corpse.png";
    public static final String SKELETON_JSON = "PackmasterAnim.json";
    public static final String SKELETON_ATLAS = "PackmasterAnim.atlas";
    private static final String[] orbTextures = {
            modID + "Resources/images/char/mainChar/orb/layer1-bag.png",
            modID + "Resources/images/char/mainChar/orb/layer2-bag.png",
            modID + "Resources/images/char/mainChar/orb/layer3.png",
            modID + "Resources/images/char/mainChar/orb/layer4-bag.png",
            modID + "Resources/images/char/mainChar/orb/layer5-bag.png",
            modID + "Resources/images/char/mainChar/orb/layer6-bag.png",
            modID + "Resources/images/char/mainChar/orb/layer1-bag.png",
            modID + "Resources/images/char/mainChar/orb/layer2-bagd.png",
            modID + "Resources/images/char/mainChar/orb/layer3d.png",
            modID + "Resources/images/char/mainChar/orb/layer4-bagd.png",
            modID + "Resources/images/char/mainChar/orb/layer5-bagd.png",};
    static final String ID = makeID("ThePackmaster");
    static final CharacterStrings characterStrings = CardCrawlGame.languagePack.getCharacterString(ID);
    static final String[] NAMES = characterStrings.NAMES;
    static final String[] TEXT = characterStrings.TEXT;
    private static final float HAT_Y_OFF = 13f;
    public static float update_timer = 0;
    public static boolean glow_fade = false;


    public ThePackmaster(String name, PlayerClass setClass) {
        super(name, setClass, new CustomEnergyOrb(orbTextures, modID + "Resources/images/char/mainChar/orb/vfx.png", null), null, null);
        initializeClass(null,
                null, //Fixes crash this would cause in SkinSystemPatches
                null,
                null,
                getLoadout(), 0.0F, -10.0F, 206.0F, 230.0F, new EnergyManager(3));

        SpireAnniversary5Mod.skinManager.loadCurrentSkin(this);

        dialogX = (drawX + 0.0F * Settings.scale);
        dialogY = (drawY + 240.0F * Settings.scale);

    }

    @Override
    public CharSelectInfo getLoadout() {
        return new CharSelectInfo(NAMES[0], TEXT[0],
                75, 75, 3, 99, 5, this, getStartingRelics(),
                getStartingDeck(), false);
    }


    public void damage(DamageInfo info) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.output - this.currentBlock > 0) {
            AnimationState.TrackEntry e = this.state.setAnimation(0, "Hit", false);
            this.state.addAnimation(0, "Idle", true, 0.0F);
            e.setTimeScale(0.6F);
        }

        super.damage(info);
    }

    @Override
    public ArrayList<String> getStartingDeck() {
        ArrayList<String> retVal = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            retVal.add(Strike.ID);
        }
        for (int i = 0; i < 4; i++) {
            retVal.add(Defend.ID);
        }
        retVal.add(Rummage.ID);
        retVal.add(Cardistry.ID);
        return retVal;
    }

    public ArrayList<String> getStartingRelics() {
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(HandyHaversack.ID);
        return retVal;
    }

    @Override
    public void doCharSelectScreenSelectEffect() {
        CardCrawlGame.sound.playA("UNLOCK_PING", MathUtils.random(-0.2F, 0.2F));
        CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT,
                false);
    }

    @Override
    public String getCustomModeCharacterButtonSoundKey() {
        return "UNLOCK_PING";
    }

    @Override
    public int getAscensionMaxHPLoss() {
        return 4;
    }

    @Override
    public AbstractCard.CardColor getCardColor() {
        return PACKMASTER_RAINBOW;
    }

    @Override
    public Color getCardTrailColor() {
        return characterColor.cpy();
    }

    @Override
    public BitmapFont getEnergyNumFont() {
        return FontHelper.energyNumFontRed;
    }

    @Override
    public String getLocalizedCharacterName() {
        return NAMES[0];
    }

    @Override
    public AbstractCard getStartCardForEvent() {
        return new Rummage();
    }

    @Override
    public String getTitle(AbstractPlayer.PlayerClass playerClass) {
        return NAMES[1];
    }

    @Override
    public AbstractPlayer newInstance() {
        return new ThePackmaster(name, chosenClass);
    }

    @Override
    public Color getCardRenderColor() {
        return characterColor.cpy();
    }

    @Override
    public Color getSlashAttackColor() {
        return characterColor.cpy();
    }

    @Override
    public AbstractGameAction.AttackEffect[] getSpireHeartSlashEffect() {
        return new AbstractGameAction.AttackEffect[]{
                AbstractGameAction.AttackEffect.FIRE,
                AbstractGameAction.AttackEffect.BLUNT_HEAVY,
                AbstractGameAction.AttackEffect.FIRE};
    }

    @Override
    public String getSpireHeartText() {
        return TEXT[1];
    }

    @Override
    public String getVampireText() {
        return TEXT[2];
    }

    @Override
    public void updateVictoryVfx(ArrayList<AbstractGameEffect> effects) {
        if (!glow_fade) {
            effects.add(new VictoryGlow());
            glow_fade = true;
        }

        update_timer += Gdx.graphics.getDeltaTime();

        for (float i = 0; i + (1.0 / 30.0) <= update_timer; update_timer -= (1.0 / 30.0)) {
            effects.add(new VictoryConfettiEffect());
        }
    }

    public static class Enums {
        @SpireEnum
        public static AbstractPlayer.PlayerClass THE_PACKMASTER;
        @SpireEnum(name = "PACKMASTER_RAINBOW")
        public static AbstractCard.CardColor PACKMASTER_RAINBOW;
        @SpireEnum(name = "PACKMASTER_RAINBOW")
        @SuppressWarnings("unused")
        public static CardLibrary.LibraryType LIBRARY_COLOR;
    }

    @Override
    public ArrayList<AbstractCard> getCardPool(ArrayList<AbstractCard> tmpPool) {
        ArrayList<AbstractCard> poolCards = new ArrayList<>();
        for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks) {
            for (AbstractCard c : pack.cards) {
                poolCards.add(c);
                switch (c.rarity) {
                    case COMMON:
                        AbstractDungeon.commonCardPool.addToTop(c);
                        break;
                    case UNCOMMON:
                        AbstractDungeon.uncommonCardPool.addToTop(c);
                        break;
                    case RARE:
                        AbstractDungeon.rareCardPool.addToTop(c);
                        break;
                }
            }
        }

        return poolCards;
    }

    public void loadSkinData(AbstractSkin skin) {
        shoulderImg = TexLoader.getTexture(skin.getShoulder1Path());
        shoulder2Img = TexLoader.getTexture(skin.getShoulder2Path());
        corpseImg = TexLoader.getTexture(skin.getCorpsePath());

        //Memory leak fixed in SkinSystemPatches
        loadAnimation(
                skin.getSkeletonAtlasPath(),
                skin.getSkeletonJSONPath(),
                skin.getScale()
        );
        AnimationState.TrackEntry e = state.setAnimation(0, "Idle", true);
        this.stateData.setMix("Hit", "Idle", 0.1F);
        e.setTime(e.getEndTime() * MathUtils.random());
    }

    @Override
    public void dispose() {
        //Please don't dispose our cached textures, thanks
    }

    protected Bone headBone;
    protected Slot headSlot;
    protected RegionAttachment attachment;

    @Override
    protected void loadAnimation(String atlasUrl, String skeletonUrl, float scale) {
        super.loadAnimation(atlasUrl, skeletonUrl, scale);
        invalidateHatVariables();
        if (HatsManager.currentHat != null) {
            setUpHat(HatsManager.currentHat);
        }
    }

    public void setUpHat(String hatID) {
        if (headBone == null) {
            findHatFields();
        }

        if(HatMenu.specialHats.containsKey(hatID)) {
            hatID = "No"; //Don't render hat on special hats
        }

        if (checkHat(hatID))
            return;
        setHat(hatID);
    }

    protected boolean checkHat(String hatID) {
        String imgPath = HatsManager.getImagePathFromHatID(hatID);
        if (!TexLoader.testTexture(imgPath)) {
            resetHat();
            return true;
        }
        return false;
    }

    protected void setHat(String hatID) {
        String imgPath = HatsManager.getImagePathFromHatID(hatID);
        TextureRegion region = TexLoader.getTextureAsAtlasRegion(imgPath);

        if (attachment == null) {
            String attachName = headBone.toString();

            // Create a new slot for the attachment
            Slot origSlot = headSlot;
            Slot slotClone = new Slot(new SlotData(skeleton.getSlots().size, attachName, origSlot.getBone().getData()), origSlot.getBone());
            slotClone.getData().setBlendMode(origSlot.getData().getBlendMode());
            skeleton.getSlots().add(slotClone);

            Array<Slot> drawOrder = skeleton.getDrawOrder();
            drawOrder.add(slotClone);
            skeleton.setDrawOrder(drawOrder);

            attachment = new RegionAttachment("hat");
            attachment.setRegion(region);
            attachment.setWidth(region.getRegionWidth());
            attachment.setHeight(region.getRegionHeight());
            attachment.setX(1F);
            attachment.setY(38F * Settings.scale);
            attachment.setScaleX(Settings.scale);
            attachment.setScaleY(Settings.scale);
            attachment.updateOffset();

            Skin skin = skeleton.getData().getDefaultSkin();
            skin.addAttachment(slotClone.getData().getIndex(), attachment.getName(), attachment);

            slotClone.getData().setAttachmentName(attachment.getName());
            skeleton.setAttachment(attachName, attachment.getName());

            skeleton.findBone("HatBone").setScale(0F);
            AbstractCardPack p = SpireAnniversary5Mod.packsByID.get(hatID);
            if (p != null && p.hatHidesHair) {
                skeleton.findBone("HairBone").setScale(0F);
            }
        } else {
            attachment.setRegion(region);

            skeleton.findBone("HatBone").setScale(0F);
            AbstractCardPack p = SpireAnniversary5Mod.packsByID.get(hatID);
            if (p != null && p.hatHidesHair) {
                skeleton.findBone("HairBone").setScale(0F);
            } else {
                skeleton.findBone("HairBone").setScale(1F);
            }
        }
    }

    public void resetHat() {
        skeleton.findBone("HatBone").setScale(1F);
        skeleton.findBone("HairBone").setScale(1F);
        String imgPath = HatsManager.getImagePathFromHatID("No");
        if (attachment != null) {
            TextureRegion region = TexLoader.getTextureAsAtlasRegion(imgPath);
            attachment.setRegion(region);
        }
    }

    protected void findHatFields() {
        Array<Bone> possiblebones = skeleton.getBones();
        for (Bone b : possiblebones) {
            if (b.toString().toLowerCase(Locale.ROOT).equals("head")) {
                headBone = b;
                break;
            }
        }

        Array<Slot> possibleslots = skeleton.getSlots();
        for (Slot s : possibleslots) {
            if (s.getBone().toString().toLowerCase(Locale.ROOT).equals("head")) {
                headSlot = s;
                break;
            }
        }
    }

    protected void invalidateHatVariables() {
        headBone = null;
        headSlot = null;
        attachment = null;
    }

    @Override
    public void renderPlayerImage(SpriteBatch sb) {
        if(headSlot == null) {
            findHatFields();
        }
        float x = skeleton.getX() + headSlot.getBone().getWorldX();
        float y = skeleton.getY() + headSlot.getBone().getWorldY() - HAT_Y_OFF * headSlot.getBone().getScaleY();
        HatsManager.preRenderPlayer(sb, this, x, y);
        super.renderPlayerImage(sb);
        HatsManager.postRenderPlayer(sb, this, x, y);
    }

    @Override
    public List<CutscenePanel> getCutscenePanels() {
        ArrayList<CutscenePanel> panels = new ArrayList<>();

        glow_fade = false;

        panels.add(new CutscenePanel(makeImagePath("ending/EndingSlice_1.png"), "ATTACK_DAGGER_2"));
        panels.add(new CutscenePanel(makeImagePath("ending/EndingSlice_2.png")));
        panels.add(new CutscenePanel(makeImagePath("ending/EndingSlice_3.png")));
        return panels;
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }
}
