package thePackmaster.packs;

import basemod.AutoAdd;
import basemod.ReflectionHacks;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.evacipated.cardcrawl.modthespire.lib.SpireOverride;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.hats.HatMenu;
import thePackmaster.util.CardArtRoller;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;
import static thePackmaster.SpireAnniversary5Mod.modID;

@AutoAdd.Ignore
public class PackPreviewCard extends CustomCard {
    public static final String ID = SpireAnniversary5Mod.makeID("PackPreviewCard");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    private Color typeColor = new Color(0.35F, 0.35F, 0.35F, 1f);
    protected String author;
    protected String parentID;

    private boolean needsArtRefresh = false;

    public static AbstractCardPack parentPack;

    public PackPreviewCard(final String cardID, AbstractCardPack owningParent) {
        this(cardID, getCardTextureString(cardID.replace(modID + ":", ""), CardType.SKILL), owningParent);
    }

    public PackPreviewCard(final String cardID, final String img, AbstractCardPack owningParent) {
        super(cardID, "", img,
                -2, "", CardType.SKILL, ThePackmaster.Enums.PACKMASTER_RAINBOW, CardRarity.SPECIAL, CardTarget.SELF);
        parentPack = owningParent;
        rawDescription = parentPack.description;
        name = originalName = parentPack.name;
        author = parentPack.author;
        parentID = parentPack.packID;
        initializeTitle();
        initializeDescription();
        setBackgroundTextures();
    }

    public PackPreviewCard(String cardID, AbstractCardPack parentPack, String basegameImg) {
        this(cardID, null, parentPack);
        this.portrait = ((TextureAtlas) ReflectionHacks.getPrivateStatic(AbstractCard.class, "cardAtlas")).findRegion(basegameImg);
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg != null && textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        } else {
            return super.getPortraitImage();
        }
    }

    public static String getCardTextureString(final String cardName, final CardType cardType) {
        String textureString;

        switch (cardType) {
            case ATTACK:
            case POWER:
            case SKILL:
                textureString = makeImagePath("cards/" + cardName + ".png");
                break;
            default:
                textureString = makeImagePath("ui/missing.png");
                break;
        }

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    public void upgrade() {}
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @SpireOverride
    protected void renderType(SpriteBatch sb) {
        String text = UI_STRINGS.TEXT[1];
        BitmapFont font = FontHelper.cardTypeFont;
        font.getData().setScale(this.drawScale);
        FontHelper.renderRotatedText(sb, font, text, this.current_x, this.current_y - 22.0F * this.drawScale * Settings.scale, 0.0F, -1.0F * this.drawScale * Settings.scale, this.angle, false, this.typeColor);
    }

    public void renderAuthorText(SpriteBatch sb) {
        float xPos, yPos, offsetY;
        BitmapFont font;
        String text = UI_STRINGS.TEXT[0] + author;
        if (author.isEmpty() || this.isFlipped || this.isLocked || this.transparency <= 0.0F)
            return;
        font = FontHelper.cardTitleFont;
        xPos = this.current_x;
        yPos = this.current_y;
        offsetY = -410.0F * Settings.scale * this.drawScale / 2.0F;
        BitmapFont.BitmapFontData fontData = font.getData();
        float originalScale = fontData.scaleX;
        float scaleMulti = 1.1F;
        int length = text.length();
        if (length > 18) {
            scaleMulti -= 0.02F * (length - 20);
            if (scaleMulti < 0.5F)
                scaleMulti = 0.5F;
        }
        fontData.setScale(scaleMulti * (this.drawScale * 0.85f));
        Color color = Settings.CREAM_COLOR.cpy();
        color.a = this.transparency;
        FontHelper.renderRotatedText(sb, font, text, xPos, yPos, 0.0F, offsetY, this.angle, true, color);
        fontData.setScale(originalScale);
    }

    public void renderHatText(SpriteBatch sb) {
        //Don't show with Banishing Decree and the like
        if(CardCrawlGame.isInARun() && AbstractDungeon.floorNum > 1) return;

        float xPos, yPos, offsetY;
        BitmapFont font;
        boolean hatUnlocked = HatMenu.currentlyUnlockedHats.contains(parentID);
        boolean rainbowUnlocked = HatMenu.currentlyUnlockedRainbows.contains(parentID);
        String text;
        if(hatUnlocked)
            text = UI_STRINGS.TEXT[2];
        else
            text = UI_STRINGS.TEXT[3];

        if (author.isEmpty() || this.isFlipped || this.isLocked || this.transparency <= 0.0F)
            return;
        font = FontHelper.cardTitleFont;
        xPos = this.current_x;
        yPos = this.current_y;
        offsetY = 413.0F * Settings.scale * this.drawScale / 2.0F;
        BitmapFont.BitmapFontData fontData = font.getData();
        float originalScale = fontData.scaleX;
        float scaleMulti = 1.1F;
        int length = text.length();
        if (length > 18) {
            scaleMulti -= 0.02F * (length - 20);
            if (scaleMulti < 0.5F)
                scaleMulti = 0.5F;
        }
        fontData.setScale(scaleMulti * (this.drawScale * 0.85f));
        Color color;
        if (rainbowUnlocked) {
            color = new Color(0f,0.9f,1f,1f);
        } else if (hatUnlocked) {
            color = Settings.GREEN_TEXT_COLOR;
        } else {
            color = Settings.RED_TEXT_COLOR;
        }
        color.a = this.transparency;
        FontHelper.renderRotatedText(sb, font, text, xPos, yPos, 0.0F, offsetY, this.angle, true, color);
        fontData.setScale(originalScale);
    }

    private static final float SHADOW_TRANSITION_TIME = 0.3f;
    private static final float SHADOWED_SHADE = 0.5f;
    private float shadowTime = 0f;
    private boolean shadow = false;

    public void shadow() {
        if (!shadow) {
            shadow = true;
            shadowTime = SHADOW_TRANSITION_TIME;
        }
    }
    public void noShadow() {
        if (shadow) {
            shadow = false;
            shadowTime = SHADOW_TRANSITION_TIME;
        }
    }

    @Override
    public void update() {
        super.update();

        if (shadowTime > 0) {
            shadowTime = Math.max(0, shadowTime - Gdx.graphics.getDeltaTime());
            float prog = 1 - (shadowTime / SHADOW_TRANSITION_TIME);
            Color renderColor = ReflectionHacks.getPrivate(this, AbstractCard.class, "renderColor");
            if (shadow) {
                renderColor.r = Interpolation.smooth.apply(1f, SHADOWED_SHADE, prog);
                renderColor.g = Interpolation.smooth.apply(1f, SHADOWED_SHADE, prog);
                renderColor.b = Interpolation.smooth.apply(1f, SHADOWED_SHADE, prog);
            }
            else {
                renderColor.r = Interpolation.smooth.apply(SHADOWED_SHADE, 1f, prog);
                renderColor.g = Interpolation.smooth.apply(SHADOWED_SHADE, 1f, prog);
                renderColor.b = Interpolation.smooth.apply(SHADOWED_SHADE, 1f, prog);
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        renderAuthorText(sb);
        renderHatText(sb);
    }

    @Override
    public void renderInLibrary(SpriteBatch sb) {
        super.renderInLibrary(sb);
        if (!(SingleCardViewPopup.isViewingUpgrade && this.isSeen && !this.isLocked)) {
            renderAuthorText(sb);
        }
    }

    private void setBackgroundTextures() {
        String path512 = "";
        String path1024 = "";
        String name = parentPack.getClass().getSimpleName();
        if (!Gdx.files.internal("anniv5Resources/images/512/"+ name +".png").exists()) {
            path512 = "anniv5Resources/images/512/boosterpackframe.png";
        } else {
            path512 = "anniv5Resources/images/512/"+ name +".png";
        }
        if (!Gdx.files.internal("anniv5Resources/images/1024/"+ name +".png").exists()) {
            path1024 = "anniv5Resources/images/1024/boosterpackframe.png";
        } else {
            path1024 = "anniv5Resources/images/1024/"+ name +".png";
        }
        this.setBackgroundTexture(path512, path1024);
    }
}
