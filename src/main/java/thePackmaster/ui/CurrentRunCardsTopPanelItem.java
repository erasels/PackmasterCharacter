package thePackmaster.ui;

import basemod.TopPanelItem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Interpolation;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.select.GridCardSelectScreen;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.ThePackmaster;
import thePackmaster.packs.AbstractCardPack;
import thePackmaster.util.TexLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen.GRID;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.CurrentScreen.NONE;
import static thePackmaster.SpireAnniversary5Mod.selectedCards;

public class CurrentRunCardsTopPanelItem extends TopPanelItem {
    private static final float tipYpos = Settings.HEIGHT - (120.0f * Settings.scale);
    private static final float FLASH_ANIM_TIME = 2.0F;

    public static final String ID = SpireAnniversary5Mod.makeID("CurrentRunCardsTopPanelItem");
    private static final Texture IMAGE = TexLoader.getTexture(SpireAnniversary5Mod.makeImagePath("ui/CurrentRunCardsTopPanelItem.png"));
    private static final UIStrings STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    private static final String[] TEXT = STRINGS.TEXT;

    private boolean open = false;

    public float flashTimer;

    public CurrentRunCardsTopPanelItem() {
        super(IMAGE, ID);
    }

    @Override
    public boolean isClickable() {
        return true;// selectedCards;
    }

    @Override
    public void update() {
        updateFlash();
        super.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);
        renderFlash(sb);
        renderHover(sb);
    }

    @Override
    protected void onClick() {
        if (isClickable()) {
            if (open && AbstractDungeon.isScreenUp) {
                if (AbstractDungeon.screen == GRID) {
                    AbstractDungeon.closeCurrentScreen();
                    CardCrawlGame.sound.play("MAP_CLOSE");
                }
                open = false;
            }
            else if (AbstractDungeon.previousScreen == null) { //Don't allow opening a third layer and losing a screen
                CardCrawlGame.sound.play("RELIC_DROP_MAGICAL");
                CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);

                for (AbstractCardPack pack : SpireAnniversary5Mod.currentPoolPacks) {
                    List<AbstractCard> cards = new ArrayList<>(pack.cards);
                    cards.sort((c1, c2) -> {
                        if (c1.rarity != c2.rarity) {
                            return rarityToInt(c1.rarity) < rarityToInt(c2.rarity) ? -1 : 1;
                        }
                        return c1.name.compareTo(c2.name);
                    });
                    for (AbstractCard card : cards) {
                        // Packs shouldn't have rarities other than common, uncommon, and rare in them, and if any cards sneak
                        // through they still won't show up in the draft, so don't show them as part of the card pool
                        if (card.rarity == AbstractCard.CardRarity.COMMON || card.rarity == AbstractCard.CardRarity.UNCOMMON || card.rarity == AbstractCard.CardRarity.RARE) {
                            group.addToTop(card);
                        }
                    }
                }

                if (AbstractDungeon.screen != NONE)
                    AbstractDungeon.previousScreen = AbstractDungeon.screen;
                AbstractDungeon.gridSelectScreen.open(group, 0, true, TEXT[2]);
                open = true;
            }
        }
    }

    private int rarityToInt(AbstractCard.CardRarity rarity) {
        switch (rarity) {
            case COMMON:
                return 0;
            case UNCOMMON:
                return 1;
            case RARE:
                return 2;
            default:
                return 3;
        }
    }

    public void flash() {
        this.flashTimer = FLASH_ANIM_TIME;
    }

    private void updateFlash() {
        if(flashTimer != 0.0f) {
            flashTimer -= Gdx.graphics.getDeltaTime();
        }
    }

    public void renderHover(SpriteBatch sb) {
        if (this.getHitbox().hovered) {
            float xPos = this.x - this.hb_w;
            String packNames = SpireAnniversary5Mod.currentPoolPacks.stream().map(p -> p.name).collect(Collectors.joining(" NL " ));
            String text = TEXT[1].replace("{0}", packNames);
            if(selectedCards) {
                TipHelper.renderGenericTip(xPos, tipYpos, TEXT[0], text);
            } else {
                TipHelper.renderGenericTip(xPos, tipYpos, TEXT[0], packNames); //if you haven't selected yet, don't tell the player the book is clickable
            }
        }
    }

    public void renderFlash(SpriteBatch sb) {
        float tmp = Interpolation.exp10In.apply(0.0F, 4.0F, flashTimer / FLASH_ANIM_TIME);
        sb.setBlendFunction(770, 1);
        sb.setColor(new Color(1.0F, 1.0F, 1.0F, flashTimer * FLASH_ANIM_TIME));

        float halfWidth = (float)this.image.getWidth() / 2.0F;
        float halfHeight = (float)this.image.getHeight() / 2.0F;
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, (float)this.image.getWidth(), (float)this.image.getHeight(), Settings.scale+tmp, Settings.scale+tmp, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, (float)this.image.getWidth(), (float)this.image.getHeight(), Settings.scale+tmp* 0.66F, Settings.scale+tmp* 0.66F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);
        sb.draw(this.image, this.x - halfWidth + halfHeight * Settings.scale, this.y - halfHeight + halfHeight * Settings.scale, halfWidth, halfHeight, (float)this.image.getWidth(), (float)this.image.getHeight(), Settings.scale+tmp/ 3.0F, Settings.scale+tmp/ 3.0F, this.angle, 0, 0, this.image.getWidth(), this.image.getHeight(), false, false);

        sb.setBlendFunction(770, 771);
    }
}