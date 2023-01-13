package thePackmaster.vfx.rippack;

import basemod.helpers.VfxBuilder;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import static thePackmaster.SpireAnniversary5Mod.makeID;
import static thePackmaster.SpireAnniversary5Mod.modID;

public class FlimsyBashEffect {

    public static Texture MORNING_STAR = new Texture(modID + "Resources/images/vfx/rippack/morningStar.png");
    private static float scale = 0.7f;

    public static AbstractGameEffect ChuckIt(AbstractCreature c) {
        AbstractPlayer p = AbstractDungeon.player;
        float startX = p.hb.cX + 50.0f;
        float startY = p.hb.cY + 50.0f;
        float aboveHeadX = startX - 100.0f;
        float aboveHeadY = startY + 100.0f;
        return new VfxBuilder(MORNING_STAR, p.hb.cX, p.hb.cY, 0.3f)
                .setScale(scale)
                .moveX(startX, aboveHeadX)
                .moveY(startY, aboveHeadY)
                .rotate(5.0f)
                .emitEvery(
                        (x, y) -> new VfxBuilder(MORNING_STAR, x, y, 0.5f)
                                .fadeOutFromAlpha(0.5f, 0.4f)
                                .setScale(scale)
                                .build(), 0.01f)
                .andThen(0.2f)
                .andThen(0.25f)
                .emitEvery(
                        (x, y) -> new VfxBuilder(MORNING_STAR, x, y, 0.5f)
                                .fadeOutFromAlpha(0.5f, 0.4f)
                                .setScale(scale)
                                .build(), 0.01f)
                .playSoundAt(0.01f, makeID("RipPack_MorningStarThrow"))
                .rotate(-1000.0f)
                .moveX(aboveHeadX, c.hb.cX)
                .moveY(aboveHeadY, c.hb.cY)
                .build();
    }
    public static AbstractGameEffect Drop(AbstractCreature c) {
        return new VfxBuilder(MORNING_STAR, c.hb.cX, c.hb.cY, 1.0f)
                .moveX(c.hb.cX, c.hb.cX - 50.0f * Settings.scale)
                .moveY(c.hb.cY, c.hb.cY - (c.hb.y / 2.0f))
                .setScale(scale)
                .rotate(50.0f)
                .fadeOut(1.5f)
                .build();
    }
}
