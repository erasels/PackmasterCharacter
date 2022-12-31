package thePackmaster.packs;

import basemod.AutoAdd;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import thePackmaster.ThePackmaster;
import thePackmaster.util.CardArtRoller;

import static thePackmaster.SpireAnniversary5Mod.makeImagePath;
import static thePackmaster.SpireAnniversary5Mod.modID;

@AutoAdd.Ignore
public abstract class AbstractPackPreviewCard extends CustomCard {

    private boolean needsArtRefresh = false;

    public static AbstractCardPack parentPack;

    public AbstractPackPreviewCard(final String cardID, AbstractCardPack owningParent) {
        super(cardID, "", getCardTextureString(cardID.replace(modID + ":", ""), CardType.SKILL),
                -2, "", CardType.SKILL, ThePackmaster.Enums.PACKMASTER_RAINBOW, CardRarity.SPECIAL, CardTarget.SELF);
        parentPack = owningParent;
        rawDescription = parentPack.description;
        name = originalName = parentPack.name;
        initializeTitle();
        initializeDescription();
        this.setBackgroundTexture("anniv5Resources/images/512/boosterpackframe.png", "anniv5Resources/images/1024/boosterpackframe.png");

        //TODO - change its type to 'Pack', or just don't render the type text.
    }

    @Override
    protected Texture getPortraitImage() {
        if (textureImg.contains("ui/missing.png")) {
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

    public void upgrade() {

    }
}
