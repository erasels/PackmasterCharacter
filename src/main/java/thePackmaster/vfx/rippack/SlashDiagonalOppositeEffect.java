package thePackmaster.vfx.rippack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SlashDiagonalOppositeEffect extends AbstractGameEffect {

    public TextureAtlas.AtlasRegion img;

    private float x;

    private float y;

    private float sY;

    private float tY;

    public SlashDiagonalOppositeEffect(float x, float y, boolean mute) {
        this.duration = 0.6F;
        this.startingDuration = 0.6F;
        this.img = ImageMaster.ATK_SLASH_D;
        TextureAtlas.AtlasRegion flippedImg = new TextureAtlas.AtlasRegion(img);
        flippedImg.flip(true, false);
        this.img = flippedImg;
        if (this.img != null) {
            this.x = x - img.packedWidth / 2.0F;
            y -= img.packedHeight / 2.0F;
        }
        this.color = Color.WHITE.cpy();
        this.scale = Settings.scale;
        if(!mute) {
            CardCrawlGame.sound.play("ATTACK_FAST");
        }
        this.y = y;
        this.sY = y;
        this.tY = y;
    }

    public void render(SpriteBatch sb) {
        if (img != null) {
            sb.setColor(this.color);
            sb.draw(img, x, y, img.packedWidth / 2.0F, img.packedHeight / 2.0F, img.packedWidth, img.packedHeight, scale, scale, rotation);
        }
    }

    public void dispose() {}
}

