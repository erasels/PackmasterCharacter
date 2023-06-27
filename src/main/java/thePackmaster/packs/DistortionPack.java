package thePackmaster.packs;

import basemod.Pair;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import thePackmaster.SpireAnniversary5Mod;
import thePackmaster.cards.AbstractPackmasterCard;
import thePackmaster.cards.distortionpack.*;
import thePackmaster.vfx.distortionpack.ImproveEffect;

import java.util.ArrayList;
import java.util.List;

import static thePackmaster.SpireAnniversary5Mod.modID;

public class DistortionPack extends AbstractCardPack {
    public static final String ID = SpireAnniversary5Mod.makeID("DistortionPack");
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(ID);
    public static final String NAME = UI_STRINGS.TEXT[0];
    public static final String DESC = UI_STRINGS.TEXT[1];
    public static final String AUTHOR = UI_STRINGS.TEXT[2];

    public DistortionPack() {
        super(ID, NAME, DESC, AUTHOR, new AbstractCardPack.PackSummary(4, 2, 3, 4, 4, "Exhaust"));
    }

    @Override
    public ArrayList<String> getCards() {
        ArrayList<String> cards = new ArrayList<>();
        cards.add(S_r_ke.ID);
        cards.add(BurningAct.ID);
        cards.add(CursedCircle.ID);
        cards.add(Darken.ID);
        cards.add(Deconstruct.ID);
        cards.add(Deterioration.ID);
        cards.add(MindMaze.ID);
        cards.add(Rue.ID);
        cards.add(Shatter.ID);
        cards.add(Static.ID);
        return cards;
    }

    @Override
    public PackPreviewCard makePreviewCard() {
        return new DistortionPackPreview(packID, this);
    }

    private static class DistortionPackPreview extends PackPreviewCard {
        private static Texture distortionPackTexture = null;

        public DistortionPackPreview(String cardID, AbstractCardPack parentPack) {
            super(cardID, parentPack);

            if (distortionPackTexture == null) {
                List<Pair<String, Texture>> skillTextures = new ArrayList<>();
                for (AbstractCard c : CardLibrary.getAllCards()) {
                    if (c instanceof AbstractPackmasterCard && c.type == CardType.SKILL
                        && c.portrait != null) {
                        String path = getCardTextureString(c.cardID.replace(modID + ":", ""), CardType.SKILL);
                        Texture imgFromMap = imgMap.get(path);
                        if (imgFromMap != null && imgFromMap.getWidth() == 250 && imgFromMap.getHeight() == 190) {
                            skillTextures.add(new Pair<>(path, imgFromMap));
                        }
                    }
                }

                Texture cardTexture = null;
                if (!skillTextures.isEmpty()) {
                    Pair<String, Texture> chosenImg = skillTextures.get(MathUtils.random(skillTextures.size() - 1));
                    this.textureImg = chosenImg.getKey();
                    cardTexture = chosenImg.getValue();
                }
                else {
                    this.textureImg = getCardTextureString(S_r_ke.ID.replace(modID + ":", ""), CardType.SKILL);
                    if (imgMap.containsKey(textureImg)) {
                        cardTexture = imgMap.get(textureImg);
                    } else {
                        cardTexture = ImageMaster.loadImage(textureImg);
                        cardTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
                        imgMap.put(textureImg, cardTexture);
                    }
                }

                distortionPackTexture = ImproveEffect._refactor(cardTexture, false);
            }

            int tw = distortionPackTexture.getWidth();
            int th = distortionPackTexture.getHeight();
            this.portrait = this.jokePortrait = new TextureAtlas.AtlasRegion(distortionPackTexture, 0, 0, tw, th);
        }
    }
}
