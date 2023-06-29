package thePackmaster.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.patches.rippack.AllCardsRippablePatches;
import thePackmaster.util.CardArtRoller;
import thePackmaster.util.Wiz;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;
import static thePackmaster.SpireAnniversary5Mod.modID;
import static thePackmaster.util.Wiz.atb;
import static thePackmaster.util.Wiz.att;

public abstract class AbstractPackmasterCard extends CustomCard {

    protected final CardStrings cardStrings;

    public int secondMagic;
    public int baseSecondMagic;
    public boolean upgradedSecondMagic;
    public boolean isSecondMagicModified;

    public int secondDamage;
    public int baseSecondDamage = -1;
    public boolean upgradedSecondDamage;
    public boolean isSecondDamageModified;

    private boolean needsArtRefresh = false;
    private String bottomText;
    private String frameFolder;
    private String orbString;

    public boolean isUnnate = false;

    public static Color packNameDisplayColor = Settings.CREAM_COLOR.cpy();

    public AbstractPackmasterCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, String frameID) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW, getCardTextureString(cardID.replace(modID + ":", ""), type), frameID, null);
    }

    public AbstractPackmasterCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, String frameID, String orbPath) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW, getCardTextureString(cardID.replace(modID + ":", ""), type), frameID, orbPath);
    }

    public AbstractPackmasterCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, String frameID) {
        this(cardID, cost, type, rarity, target, color, getCardTextureString(cardID.replace(modID + ":", ""), type), frameID, null);
    }

    public AbstractPackmasterCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, String frameID, String orbPath) {
        this(cardID, cost, type, rarity, target, color, getCardTextureString(cardID.replace(modID + ":", ""), type), frameID, orbPath);
    }

    public AbstractPackmasterCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target) {
        this(cardID, cost, type, rarity, target, ThePackmaster.Enums.PACKMASTER_RAINBOW, null, null);
    }

    public AbstractPackmasterCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color) {
        this(cardID, cost, type, rarity, target, color, getCardTextureString(cardID.replace(modID + ":", ""), type), null, null);
    }

    public AbstractPackmasterCard(final String cardID, final int cost, final CardType type, final CardRarity rarity, final CardTarget target, final CardColor color, final String textureString, String frameID, String orbPath) {
        super(cardID, "", textureString, cost, "", type, color, rarity, target);
        cardStrings = CardCrawlGame.languagePack.getCardStrings(this.cardID);
        rawDescription = cardStrings.DESCRIPTION;
        name = originalName = cardStrings.NAME;
        frameFolder = frameID;
        orbString = orbPath;
        initializeTitle();
        initializeDescription();

        if (textureImg.contains("ui/missing.png")) {
            if (CardLibrary.cards != null && !CardLibrary.cards.isEmpty()) {
                CardArtRoller.computeCard(this);
            } else
                needsArtRefresh = true;
        }

        if (color != CardColor.COLORLESS) {
            if (frameFolder == null || SpireAnniversary5Mod.oneFrameMode) {
                setBackgroundTexture(
                        "anniv5Resources/images/512/coreset/" + getTypeName() + ".png",
                        "anniv5Resources/images/1024/coreset/" + getTypeName() + ".png");
            } else {
                setBackgroundTexture("anniv5Resources/images/512/" + frameFolder + "/" + getTypeName() + ".png",
                        "anniv5Resources/images/1024/" + frameFolder + "/" + getTypeName() + ".png");

            }

            if (orbString != null && !SpireAnniversary5Mod.oneFrameMode) {
                setOrbTexture(
                        "anniv5Resources/images/512/" + orbString,
                        "anniv5Resources/images/1024/" + orbString
                );
            }
        }

    }

    private String getTypeName() {
        switch (type) {
            case ATTACK:
                return "attack";
            case POWER:
                return "power";
            default:
                return "skill";
        }
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
            return CardArtRoller.getPortraitTexture(this);
        } else {
            return super.getPortraitImage();
        }
    }

    public static String getCardTextureString(final String cardName, final AbstractCard.CardType cardType) {
        String textureString = makeImagePath("cards/" + cardName + ".png");

        FileHandle h = Gdx.files.internal(textureString);
        if (!h.exists()) {
            textureString = makeImagePath("ui/missing.png");
        }
        return textureString;
    }

    @Override
    public void applyPowers() {
        // We call the superclass's method first to maintain compatibility with mods that patch AbstractCard's method
        // Specifically, the Runesmith postfix patches methods in AbstractCard in a way that resets after the method
        // is called, so having the calculation for the base damage happen first ensures that Runesmith's enhance works
        // for Packmaster cards (though not for secondDamage -- that could be fixed too but is more complicated)
        // See https://github.com/PureStream/Runesmith/blob/d60bece6746270649e6fe2025ed133581881587f/the_runesmith/src/main/java/runesmith/patches/EnhancedCardValueModified.java
        super.applyPowers();
        if (baseSecondDamage > -1) {
            int originalBaseDamage = baseDamage;
            int originalDamage = damage;
            boolean originalIsDamageModified = isDamageModified;

            baseDamage = baseSecondDamage;
            super.applyPowers();
            isSecondDamageModified = (secondDamage != baseSecondDamage);
            secondDamage = damage;

            baseDamage = originalBaseDamage;
            damage = originalDamage;
            isDamageModified = originalIsDamageModified;
        }
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        // We call the superclass's method first to maintain compatibility with mods that patch AbstractCard's method
        // Specifically, the Runesmith postfix patches methods in AbstractCard in a way that resets after the method
        // is called, so having the calculation for the base damage happen first ensures that Runesmith's enhance works
        // for Packmaster cards (though not for secondDamage -- that could be fixed too but is more complicated)
        // See https://github.com/PureStream/Runesmith/blob/d60bece6746270649e6fe2025ed133581881587f/the_runesmith/src/main/java/runesmith/patches/EnhancedCardValueModified.java
        super.calculateCardDamage(mo);
        if (baseSecondDamage > -1) {
            int originalBaseDamage = baseDamage;
            int originalDamage = damage;
            boolean originalIsDamageModified = isDamageModified;

            baseDamage = baseSecondDamage;
            super.calculateCardDamage(mo);
            isSecondDamageModified = (secondDamage != baseSecondDamage);
            secondDamage = damage;

            baseDamage = originalBaseDamage;
            damage = originalDamage;
            isDamageModified = originalIsDamageModified;
        }
    }

    public void resetAttributes() {
        super.resetAttributes();
        secondMagic = baseSecondMagic;
        isSecondMagicModified = false;
        secondDamage = baseSecondDamage;
        isSecondDamageModified = false;
    }

    public void displayUpgrades() {
        super.displayUpgrades();
        if (upgradedSecondMagic) {
            secondMagic = baseSecondMagic;
            isSecondMagicModified = true;
        }
        if (upgradedSecondDamage) {
            secondDamage = baseSecondDamage;
            isSecondDamageModified = true;
        }
    }

    protected void upgradeSecondMagic(int amount) {
        baseSecondMagic += amount;
        secondMagic = baseSecondMagic;
        upgradedSecondMagic = true;
    }

    protected void upgradeSecondDamage(int amount) {
        baseSecondDamage += amount;
        secondDamage = baseSecondDamage;
        upgradedSecondDamage = true;
    }

    protected void uDesc() {
        rawDescription = cardStrings.UPGRADE_DESCRIPTION;
        initializeDescription();
    }

    public void downgrade() {
        if (upgraded) {
            upgraded = false;
            name = cardStrings.NAME;
            rawDescription = cardStrings.DESCRIPTION;
            initializeTitle();
            initializeDescription();
        }
    }

    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upp();
            if (cardStrings.UPGRADE_DESCRIPTION != null) {
                uDesc();
            }
        }
    }

    public abstract void upp();

    public void update() {
        super.update();
        if (needsArtRefresh) {
            CardArtRoller.computeCard(this);
        }
    }

    // These shortcuts are specifically for cards. All other shortcuts that aren't specifically for cards can go in Wiz.
    protected void dmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void dmgTop(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        att(new DamageAction(m, new DamageInfo(AbstractDungeon.player, damage, damageTypeForTurn), fx));
    }

    protected void allDmg(AbstractGameAction.AttackEffect fx) {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void allDmgTop(AbstractGameAction.AttackEffect fx) {
        att(new DamageAllEnemiesAction(AbstractDungeon.player, multiDamage, damageTypeForTurn, fx));
    }

    protected void altDmg(AbstractMonster m, AbstractGameAction.AttackEffect fx) {
        atb(new DamageAction(m, new DamageInfo(AbstractDungeon.player, secondDamage, damageTypeForTurn), fx));
    }

    protected void blck() {
        atb(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, block));
    }

    public String cardArtCopy() {
        return null;
    }

    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return null;
    }

    protected void upMagic(int x) {
        upgradeMagicNumber(x);
    }

    protected void upSecondMagic(int x) {
        upgradeSecondMagic(x);
    }

    protected void upSecondDamage(int x) {
        upgradeSecondDamage(x);
    }

    public AbstractCardPack getParent() {
        return Wiz.getPackByCard(this);
    }

    public String getParentName() {
        AbstractCardPack p = getParent();
        if (p != null)
            return p.name;
        return "No Parent Pack!";
    }

    public void renderBorderText(SpriteBatch sb) {
        renderBorderText(sb, this.current_x, this.current_y, 400, this.drawScale);
    }

    public void renderBorderText(SpriteBatch sb, float xPos, float yPos, float yOffsetBase, float scale) {
        renderBorderText(sb, xPos, yPos, yOffsetBase, scale, false);
        renderBorderText(sb, xPos, yPos, yOffsetBase, scale, true);
    }

    public void renderBorderText(SpriteBatch sb, float xPos, float yPos, float yOffsetBase, float scale, boolean renderBottom) {
        String text = renderBottom ? getBottomText() : getTopText();
        if (text != null) {
            float offsetY;
            BitmapFont font;
            if (this.isFlipped || this.isLocked || this.transparency <= 0.0F)
                return;
            font = FontHelper.cardTitleFont;
            if (renderBottom) {
                yOffsetBase *= -1;
                yOffsetBase += 15f;
            }
            offsetY = yOffsetBase * Settings.scale * scale / 2.0F;
            BitmapFont.BitmapFontData fontData = font.getData();
            float originalScale = fontData.scaleX;
            float scaleMulti = 0.8F;
            int length = text.length();
            if (length > 20) {
                scaleMulti -= 0.02F * (length - 20);
                if (scaleMulti < 0.5F)
                    scaleMulti = 0.5F;
            }
            fontData.setScale(scaleMulti * (scale * 0.85f));
            Color color = renderBottom ? getBottomTextColor().cpy() : getTopTextColor().cpy();
            color.a = this.transparency;
            FontHelper.renderRotatedText(sb, font, text, xPos, yPos, 0.0F, offsetY, this.angle, true, color);
            fontData.setScale(originalScale);
        }
    }

    public String getBottomText() {
        return null;
    }

    public String getTopText() {
        AbstractCardPack parent = getParent(); //Hide top text on ripped text cards
        if (parent != null && AllCardsRippablePatches.AbstractCardFields.ripStatus.get(this) != AllCardsRippablePatches.RipStatus.TEXT) {
            return parent.name;
        }

        return null;
    }

    public Color getTopTextColor() {
        return packNameDisplayColor;
    }

    public Color getBottomTextColor() {
        return Settings.CREAM_COLOR;
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        renderBorderText(sb);
    }

    @Override
    public void renderInLibrary(SpriteBatch sb) {
        super.renderInLibrary(sb);
        if (!(SingleCardViewPopup.isViewingUpgrade && this.isSeen && !this.isLocked)) {
            renderBorderText(sb);
        }
    }


    public int otherPacksInHandCheck() {
        long otherPacksInHand = AbstractDungeon.player.hand.group.stream()
                .map(c -> SpireAnniversary5Mod.cardParentMap.getOrDefault(c.cardID, null))
                .filter(s -> s != null && !s.equals(SpireAnniversary5Mod.cardParentMap.get(this.cardID)))
                .distinct()
                .count();
        return ((int) otherPacksInHand);
    }

    public boolean hasSynergy() {
        return otherPacksInHandCheck() >= 2;
    }
}
