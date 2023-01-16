package thePackmaster.vfx.legacypack;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

public class SlimeDropletEffect
        extends AbstractGameEffect
{
    private float effectDuration;
    private float x;
    private float y;
    private float scaleD;
    private float sY;
    private float tX;
    private float tY;
    private Texture img;

    public SlimeDropletEffect(float x, float y)
    {
        this.effectDuration = 1.0F;
        this.duration = this.effectDuration;
        this.startingDuration = this.effectDuration;

        this.x = x;
        this.y = y;
        this.scaleD = 0.0F;
        this.tX = (MathUtils.random(-4.0F, 4.0F) * Settings.scale);
        this.tY = (MathUtils.random(12.0F, 18.0F) * Settings.scale);

        int tmp = MathUtils.random(2);
        if (tmp == 0) {
            this.color = Color.CYAN.cpy();
        } else if (tmp == 1) {
            this.color = Color.SKY.cpy();
        } else {
            this.color = Color.GREEN.cpy();
        }

        tmp = MathUtils.random(2);
        if (tmp == 0) {
            this.img = ImageMaster.loadImage("anniv5Resources/images/vfx/slimeDropA.png");;
        } else if (tmp == 1) {
            this.img = ImageMaster.loadImage("anniv5Resources/images/vfx/slimeDropB.png");;
        } else {
            this.img = ImageMaster.loadImage("anniv5Resources/images/vfx/slimeDropC.png");;
        }

        this.scale = (MathUtils.random(0.1F, 0.8F) * Settings.scale);
        this.renderBehind = true;
    }

    public void update()
    {
        this.x += this.tX;
        this.y += this.tY;
        this.tY -= 0.5F;

        Interpolation.swingOut.apply(this.scale, this.scaleD, this.duration);
        super.update();
    }

    public void render(SpriteBatch sb)
    {
        sb.setColor(this.color);

        sb.draw(this.img, this.x - this.img.getWidth() / 2.0F, this.y - this.img.getHeight() / 2.0F, this.img.getWidth() / 2.0F, this.img.getHeight() / 2.0F, this.img.getWidth(), this.img.getHeight(),
                this.scale * MathUtils.random(0.7F, 1.4F), this.scale * MathUtils.random(0.7F, 1.4F),
                this.rotation, 0, 0, this.img.getWidth(), this.img.getHeight(), false, false);
        // sb.setBlendFunction(770, 1);
        // sb.draw(this.img, this.x - this.img.getWidth() / 2.0F, this.y - this.img.getHeight() / 2.0F, this.img.getWidth() / 2.0F, this.img.getHeight() / 2.0F, this.img.getWidth(), this.img.getHeight(), this.scale *

        //   MathUtils.random(0.7F, 1.4F), this.scale *
        //   MathUtils.random(0.7F, 1.4F), this.rotation);

        // sb.setBlendFunction(770, 771);
    }

    public void dispose() {
        img.dispose();
    }
}